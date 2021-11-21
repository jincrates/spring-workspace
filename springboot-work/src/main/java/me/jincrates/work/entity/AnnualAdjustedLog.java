package me.jincrates.work.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnnualAdjustedLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjusted_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private Member member;  //사원키

    @Column(nullable = false)
    private double adjusted;   //조정일수

    @Column(nullable = false)
    private Date adjustedDate;  //조정일

    @Column(nullable = false)
    private String reason;  //사유

    @Column(nullable = false)
    private String register;  //등록자
}
