package me.jincrates.reactivebook.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.reactivebook.domain.carts.Cart;
import me.jincrates.reactivebook.domain.carts.CartItem;
import me.jincrates.reactivebook.domain.carts.CartRepository;
import me.jincrates.reactivebook.domain.items.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        /*
        람다식, 자바 스트림 API는 컬렉션에 포함된 원소들을 반복 탐색하고 필요한 값을 찾을 수 있게 해주는 새로운 접근방식이다.

        for문, if문을 사용하면 직관적이고 쉽게 코드를 작성할 수 있는데
        리액티브 프로그래밍에서는 왜 이런 방식을 사용하지 않는 걸까?
        이유는 부수 효과(size effect)때문이다.

        명령형 프로그래밍에서는 모든 로컬 변수에 부수 효과가 발생할 수 있다.
        상태를 만들면 이 상태 값을 바꿀 수 있는 수많은 다른 API를 거치는 중간 어딘가에서 값이
        잘못될 위험성도 함께 높아진다.
        자바가 강력한 타입 시스템을 가지고 있다고 하지만, 의도하지 않은 변경에 의해 발생하는 문제를 막을 수 있을 정도로 강력하지는 않다.

        스트림 API를 사용하며 이런 단점을 극복할 수 있다. p.89
         */
        return this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem().getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> {
                            return this.itemRepository.findById(id)
                                    .map(item -> new CartItem(item))
                                    .map(cartItem -> {
                                        cart.getCartItems().add(cartItem);
                                        return cart;
                                    });
                        }))
                .flatMap(cart -> this.cartRepository.save(cart))
                .thenReturn("redirect:/");
    }
}
