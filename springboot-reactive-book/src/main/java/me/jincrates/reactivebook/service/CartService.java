package me.jincrates.reactivebook.service;

import me.jincrates.reactivebook.domain.carts.Cart;
import me.jincrates.reactivebook.domain.carts.CartItem;
import me.jincrates.reactivebook.domain.carts.CartRepository;
import me.jincrates.reactivebook.domain.items.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }
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

    임시 변수가 없고 상태를 표시하는 중간 단계가 없기 때문에 코드 양이 많아졌다.
    이렇게 하면 웹 컨트롤러가 무거워지는 것은 시간 문제일 것이다.

    스프링 부트 프로젝트 리드인 필 웹(Phill Webb)은 비즈니스 로직이 아닌 웹 요청 처리만 컨트롤러가 담당하도록 만드는 것을 추천한다.
     */
    public Mono<Cart> addToCart(String cartId, String id) {
        return this.cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem().getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() ->
                                this.itemRepository.findById(id)
                                        .map(CartItem::new)
                                        .doOnNext(cartItem -> cart.getCartItems().add(cartItem))
                                        .map(cartItem -> cart)))
                .flatMap(this.cartRepository::save);
    }
}
