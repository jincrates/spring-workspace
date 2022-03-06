package me.jincrates.gobook.web.dto;

import lombok.Data;
import me.jincrates.gobook.domain.orders.Order;
import me.jincrates.gobook.domain.orders.OrderStatus;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderHistoryDto {

    private Long orderId;       //주문아이디

    private String orderDate;       //주문날짜

    private OrderStatus orderStatus;        //주문 상태

    //주문 상품 리스트
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public OrderHistoryDto(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);
    }
}
