package me.jincrates.gobook.domain.carts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.gobook.domain.BaseEntity;
import me.jincrates.gobook.domain.items.Item;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "cart_item")
@Entity
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    @Builder
    public CartItem(Cart cart, Item item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .count(count)
                .build();

        return cartItem;
    }

    public void addCount(int count) {
        this.count += count;
    }
}
