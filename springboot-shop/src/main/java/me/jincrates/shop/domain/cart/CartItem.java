package me.jincrates.shop.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.members.Member;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Table(name = "cart_item")
@Entity
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}

