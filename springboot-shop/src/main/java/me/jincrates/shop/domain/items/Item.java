package me.jincrates.shop.domain.items;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.jincrates.shop.domain.BaseTimeEntity;

@Getter
@ToString
public class Item extends BaseTimeEntity {

    private Long id;  //상품코드

    private String itemNm;  //상품명

    private int price;  //가격

    private int stockNumber;  //재고수량

    private String itemDetail;  //상품 상세설명

    private ItemSellStatus itemSellStatus;
}
