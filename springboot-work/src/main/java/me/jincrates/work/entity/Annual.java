package me.jincrates.work.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Annual extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private LocalDateTime joinDate;

    @Column(nullable = false)
    private double annual;  //발생연차

    @Column(nullable = false)
    private double month; //발생월차

    @Column(nullable = false)
    private double adjusted; //조정연차

    @Column(nullable = false)
    private double used; //사용연차
}
