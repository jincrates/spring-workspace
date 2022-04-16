package me.jincrates.work.domain.annual;

import lombok.*;
import me.jincrates.work.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnnualAdjusted extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjusted_id")
    private Long id;

    @Column(nullable = false)
    private Long baseYear;

    @Column(nullable = false)
    private String member;  //사원키

    @Column(nullable = false)
    private double adjusted;   //조정일수

    @Column(nullable = false)
    private Date adjustedDate;  //조정일

    @Column(nullable = false)
    private String reason;  //사유

    @Column(nullable = false)
    private String register;  //등록자
}
