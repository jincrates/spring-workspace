package me.jincrates.reactivebook.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.reactivebook.domain.carts.Cart;
import me.jincrates.reactivebook.domain.carts.CartItem;
import me.jincrates.reactivebook.domain.carts.CartRepository;
import me.jincrates.reactivebook.domain.items.Item;
import me.jincrates.reactivebook.domain.items.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final ReactiveFluentMongoOperations fluentOperations;

    Flux<Item> getItems() {
        // imagine calling a remote service!
        return Flux.empty();
    }

    // tag::logging[]
    Mono<Cart> addItemToCart(String cartId, String itemId) {
        return this.cartRepository.findById(cartId) //
                .log("foundCart") //
                .defaultIfEmpty(new Cart(cartId)) //
                .log("emptyCart") //
                .flatMap(cart -> cart.getCartItems().stream() //
                        .filter(cartItem -> cartItem.getItem() //
                                .getId().equals(itemId))
                        .findAny() //
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart).log("newCartItem");
                        }) //
                        .orElseGet(() -> {
                            return this.itemRepository.findById(itemId) //
                                    .log("fetchedItem") //
                                    .map(item -> new CartItem(item)) //
                                    .log("cartItem") //
                                    .map(cartItem -> {
                                        cart.getCartItems().add(cartItem);
                                        return cart;
                                    }).log("addedCartItem");
                        }))
                .log("cartWithAnotherItem") //
                .flatMap(cart -> this.cartRepository.save(cart)) //
                .log("savedCart");
    }
    // end::logging[]

    Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return itemRepository //
                            .findByNameContainingAndDescriptionContainingAllIgnoreCase( //
                                    partialName, partialDescription);
                } else {
                    return itemRepository.findByNameContainingOrDescriptionContainingAllIgnoreCase( //
                            partialName, partialDescription);
                }
            } else {
                return itemRepository.findByNameContainingIgnoreCase(partialName);
            }
        } else {
            if (partialDescription != null) {
                return itemRepository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return itemRepository.findAll();
            }
        }
    }

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0); // <1>

        ExampleMatcher matcher = (useAnd // <2>
                ? ExampleMatcher.matchingAll() //
                : ExampleMatcher.matchingAny()) //
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // <3>
                .withIgnoreCase() // <4>
                .withIgnorePaths("price"); // <5>

        Example<Item> probe = Example.of(item, matcher); // <6>

        return itemRepository.findAll(probe); // <7>
    }

    Flux<Item> searchByFluentExample(String name, String description) {
        return fluentOperations.query(Item.class) //
                .matching(query(where("TV tray").is(name).and("Smurf").is(description))) //
                .all();
    }

    Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd //
                ? ExampleMatcher.matchingAll() //
                : ExampleMatcher.matchingAny()) //
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //
                .withIgnoreCase() //
                .withIgnorePaths("price");

        return fluentOperations.query(Item.class) //
                .matching(query(byExample(Example.of(item, matcher)))) //
                .all();
    }
}
