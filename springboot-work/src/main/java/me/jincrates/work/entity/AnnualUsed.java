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
public class AnnualUsed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_id")
    private Long id;

    @Column(nullable = false)
    private Long baseYear;

    @Column(nullable = false)
    private String member;  //사원키

    @Column(nullable = false)
    private double used;   //사용일수

    @Column(nullable = false)
    private Date usedDate;  //사용일

    @Column(nullable = false)
    private String status;  //상태(Y: 사용, N: 미사용)

    @Column(nullable = false)
    private String reason;  //사유
}
