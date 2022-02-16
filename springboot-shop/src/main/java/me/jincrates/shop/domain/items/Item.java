package me.jincrates.shop.domain.items;

import lombok.*;
import me.jincrates.shop.domain.BaseEntity;
import me.jincrates.shop.exception.OutOfStockException;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "item")
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;  //상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;  //상품명

    @Column(name="price", nullable = false)
    private int price;  //가격

    @Column(nullable = false)
    private int stockNumber;  //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Builder
    public Item(String itemNm, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.itemNm = itemNm;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }

    /**
     * stock 증가
     */
    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }

    /**
     * stock 감소
     */
    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량:" + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }
}
