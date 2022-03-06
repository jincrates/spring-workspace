package me.jincrates.gobook.service;

import me.jincrates.gobook.domain.items.Item;
import me.jincrates.gobook.domain.items.ItemRepository;
import me.jincrates.gobook.domain.items.ItemSellStatus;
import me.jincrates.gobook.domain.members.Member;
import me.jincrates.gobook.domain.members.MemberRepository;
import me.jincrates.gobook.domain.orders.Order;
import me.jincrates.gobook.domain.orders.OrderItem;
import me.jincrates.gobook.domain.orders.OrderRepository;
import me.jincrates.gobook.web.dto.OrderDto;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = Member.builder()
                .email("test@email.com")
                .build();
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order() {
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = OrderDto.builder()
                .itemId(item.getId())
                .count(10)
                .build();

        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();
        System.out.println("====");
        System.out.println("orderId : " + orderId);
        System.out.println("order : " + order);
        System.out.println("orderDto : " + orderDto);
        System.out.println("orderItems : " + orderItems);

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

}