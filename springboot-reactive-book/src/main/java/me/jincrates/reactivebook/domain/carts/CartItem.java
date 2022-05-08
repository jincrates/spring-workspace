package me.jincrates.reactivebook.domain.carts;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.reactivebook.domain.items.Item;

@Data
@NoArgsConstructor
public class CartItem {

    private Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }
}
