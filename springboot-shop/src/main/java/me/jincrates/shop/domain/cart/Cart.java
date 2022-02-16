package me.jincrates.shop.domain.cart;

import lombok.*;
import me.jincrates.shop.domain.BaseEntity;
import me.jincrates.shop.domain.members.Member;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public static Cart createCart(Member member) {
       Cart cart = new Cart();
       cart.setMember(member);
       return cart;
    }
}

