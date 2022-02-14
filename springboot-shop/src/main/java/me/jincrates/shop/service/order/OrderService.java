package me.jincrates.shop.service.order;

import lombok.RequiredArgsConstructor;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.items.ItemRepository;
import me.jincrates.shop.domain.members.Member;
import me.jincrates.shop.domain.members.MemberRepository;
import me.jincrates.shop.domain.order.Order;
import me.jincrates.shop.domain.order.OrderItem;
import me.jincrates.shop.domain.order.OrderRepository;
import me.jincrates.shop.web.dto.order.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
