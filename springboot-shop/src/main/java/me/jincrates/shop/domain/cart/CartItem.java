package me.jincrates.shop.domain.cart;

import lombok.*;
import me.jincrates.shop.domain.BaseEntity;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.members.Member;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "cart_item")
@Entity
public class CartItem extends BaseEntity {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count) {
        this.count += count;
    }
}

