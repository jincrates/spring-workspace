package me.jincrates.work.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class AnnualSetting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Long id;

    @Column(nullable = false)
    private String type;  // 회계년도, 입사일

    @Column(nullable = false)
    private Date fromDate;  // 시작일

    @Column(nullable = false)
    private Date toDate;  //종료일

    @Column(nullable = false)
    private int step;  // 증가년도

    @Column(nullable = false)
    private int max;  //최대 개수

    @Column(nullable = false)
    private int min;  //최초 개수

}
