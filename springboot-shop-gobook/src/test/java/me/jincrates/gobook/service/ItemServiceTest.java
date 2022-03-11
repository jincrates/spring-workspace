package me.jincrates.gobook.service;

import me.jincrates.gobook.domain.items.*;
import me.jincrates.gobook.web.dto.ItemFormDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        //path 제일 앞에 경로를 /User로 수정함
        for (int i = 0; i < 5; i++) {
            String path = "/Users/jincrates/projects/upload/shop/item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Disabled
    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void saveItem() throws Exception {
        ItemFormDto itemFormDto = ItemFormDto.builder()
                .itemNm("테스트 상품입니다.")
                .itemSellStatus(ItemSellStatus.SELL)
                .itemDetail("테스트 상품 설명입니다.")
                .price(1000)
                .stockNumber(100)
                .build();
        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        assertEquals(itemFormDto.getItemNm(), item.getItemNm());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
    }

}