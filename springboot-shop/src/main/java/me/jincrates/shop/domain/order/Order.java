package me.jincrates.shop.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.shop.domain.BaseTimeEntity;
import me.jincrates.shop.domain.members.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString
@NoArgsConstructor
@Table(name = "orders")  // 정렬할때 order 키워드가 있어서 orders로 지정
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;  //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;  //주문상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(Member member) {
        this.member = member;
    }
}
