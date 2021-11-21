package me.jincrates.work.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Annual extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String joinDate;

    @Column(nullable = false)
    private double annual;  //발생연차

    @Column(nullable = false)
    private double month; //발생월차

    @Column(nullable = false)
    private double adjusted; //조정연차

    @Column(nullable = false)
    private double used; //사용연차
}
