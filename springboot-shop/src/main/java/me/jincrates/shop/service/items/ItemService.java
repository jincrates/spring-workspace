package me.jincrates.shop.service.items;

import lombok.RequiredArgsConstructor;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.items.ItemImg;
import me.jincrates.shop.domain.items.ItemImgRepository;
import me.jincrates.shop.domain.items.ItemRepository;
import me.jincrates.shop.web.dto.items.ItemFormDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        //상품 등록
        Item item = itemFormDto.createItem();
        //Item item = itemFormDto.toEntity(itemFormDto);
        System.out.println("===================================");
        System.out.println(item);
        itemRepository.save(item);

        //이미지 등록
        for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .repimgYn(i == 0 ? "Y" : "N")
                    .build();
            System.out.println("===================================");
            System.out.println(itemImg);

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

}
