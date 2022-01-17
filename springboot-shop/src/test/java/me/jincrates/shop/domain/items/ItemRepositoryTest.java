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
    public void 상품저장() {
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
    public void 상품저장_리스트_조회() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("테스트 상품 " + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .build();
            Item savedItem = itemRepository.save(item);
        });
    }

    @Test
    public void 상품명_조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품 1");
        printList(itemList);
    }

    @Test
    public void 상품명_OR_상품상세설명_조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품 1", "테스트 상품 상세 설명 5");
        printList(itemList);
    }

    @Test
    public void 가격_LessThan_조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        printList(itemRepository.findByPriceLessThan(10005));
    }

    @Test
    public void 가격_내림차순_조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        printList(itemList);
    }

    @Test
    public void  Query를_이용한_상품조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명 1");
        printList(itemList);
    }

    @Test
    public void  nativeQuery를_이용한_상품조회() {
        this.상품저장_리스트_조회();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명 1");
        printList(itemList);
    }

    public void printList(List<Item> itemList) {
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
}