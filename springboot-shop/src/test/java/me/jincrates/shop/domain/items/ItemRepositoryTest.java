package me.jincrates.shop.domain.items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품저장_테스트() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
        Item savedItem = itemRepository.save(item);
        System.out.print(savedItem);
    }

    @Test
    public void 상품저장_리스트_테스트() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("테스트 상품 " + i)
                    .price(10000)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .build();
            Item savedItem = itemRepository.save(item);
        });
    }

    @Test
    public void 상품명_조회_테스트() {
        this.상품저장_리스트_테스트();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품 1");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }
}