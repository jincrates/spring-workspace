package me.jincrates.shop.domain.items;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

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
        IntStream.rangeClosed(1, 70).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("테스트 상품 " + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .build();
            itemRepository.save(item);
        });
        IntStream.rangeClosed(71, 100).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("테스트 상품 " + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .stockNumber(0)
                    .build();
            itemRepository.save(item);
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

    @Test
    public void QueryDsl_조회() {
        this.상품저장_리스트_조회();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명 1" + "%"))
                .orderBy(qItem.price.desc());
        List<Item> itemList = query.fetch();
        printList(itemList);
    }

    @Test
    public void QueryDsl_상품조회() {
        this.상품저장_리스트_조회();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));

        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.print("total elements: " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        printList(resultItemList);

    }

    public void printList(List<Item> itemList) {
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
}