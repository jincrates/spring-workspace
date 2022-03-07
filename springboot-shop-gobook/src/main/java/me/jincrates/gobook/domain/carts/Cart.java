package me.jincrates.gobook.domain.carts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.gobook.domain.BaseEntity;
import me.jincrates.gobook.domain.members.Member;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public static Cart createCart(Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .build();
        return cart;
    }
}
