package me.jincrates.gobook.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.gobook.domain.carts.Cart;
import me.jincrates.gobook.domain.carts.CartItem;
import me.jincrates.gobook.domain.carts.CartItemRepository;
import me.jincrates.gobook.domain.carts.CartRepository;
import me.jincrates.gobook.domain.items.Item;
import me.jincrates.gobook.domain.items.ItemRepository;
import me.jincrates.gobook.domain.members.Member;
import me.jincrates.gobook.domain.members.MemberRepository;
import me.jincrates.gobook.web.dto.CartDetailDto;
import me.jincrates.gobook.web.dto.CartItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email) {

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email) {

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        return cartDetailDtoList;
    }
}
