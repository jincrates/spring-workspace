package me.jincrates.reactivebook.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.reactivebook.domain.carts.Cart;
import me.jincrates.reactivebook.domain.carts.CartRepository;
import me.jincrates.reactivebook.domain.items.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", this.itemRepository.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
}
