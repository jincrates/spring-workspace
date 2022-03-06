package me.jincrates.gobook.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.gobook.domain.items.Item;
import me.jincrates.gobook.domain.items.ItemImg;
import me.jincrates.gobook.domain.items.ItemImgRepository;
import me.jincrates.gobook.domain.items.ItemRepository;
import me.jincrates.gobook.domain.members.Member;
import me.jincrates.gobook.domain.members.MemberRepository;
import me.jincrates.gobook.domain.orders.Order;
import me.jincrates.gobook.domain.orders.OrderItem;
import me.jincrates.gobook.domain.orders.OrderRepository;
import me.jincrates.gobook.web.dto.OrderDto;
import me.jincrates.gobook.web.dto.OrderHistoryDto;
import me.jincrates.gobook.web.dto.OrderItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);

        System.out.println("item : " + item);
        System.out.println("member : " + member);
        System.out.println("orderItem : " + orderItem);
        System.out.println("orderItemList : " + orderItemList);
        System.out.println("order : " + order);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistoryDto> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        System.out.println("orders --------------------");
        System.out.println(orders);
        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            System.out.println("=============");
            System.out.println("orderItem : " + orderItems.toString());

            for (OrderItem orderItem : orderItems) {
                System.out.println("getId : " + orderItem.getItem().getId());
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
                System.out.println("itemImg : " + itemImg.toString());

                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }

            orderHistoryDtos.add(orderHistoryDto);
        }

        return new PageImpl<OrderHistoryDto>(orderHistoryDtos, pageable, totalCount);
    }

}
