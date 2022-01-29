package me.jincrates.shop.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.shop.domain.BaseTimeEntity;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.members.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;  //주문가격

    private int count;  //수량

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
