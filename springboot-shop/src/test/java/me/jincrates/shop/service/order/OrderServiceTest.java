package me.jincrates.shop.service.order;

import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.items.ItemRepository;
import me.jincrates.shop.domain.items.ItemSellStatus;
import me.jincrates.shop.domain.members.Member;
import me.jincrates.shop.domain.members.MemberRepository;
import me.jincrates.shop.domain.order.Order;
import me.jincrates.shop.domain.order.OrderItem;
import me.jincrates.shop.domain.order.OrderRepository;
import me.jincrates.shop.domain.order.OrderStatus;
import me.jincrates.shop.web.dto.order.OrderDto;
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
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);

        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@email.com");

        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order() {
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void cancelOrder() {
        Item item = saveItem();
        Member member = memberRepository.findByEmail("test@email.com");//saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());
        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals(100, item.getStockNumber());
    }
}