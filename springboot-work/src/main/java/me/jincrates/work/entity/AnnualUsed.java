package me.jincrates.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date usedFromDate;  //사용 시작일

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date usedToDate;  //사용 종료일

    @Column(nullable = false)
    private String reason;  //사유
}
