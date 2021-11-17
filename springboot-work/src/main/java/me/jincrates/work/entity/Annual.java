package me.jincrates.work.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Annual extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String member;

    private Date joinDate;

    private double annual;

    private double monthAnnual;

    private double adjustment;

    private double reamined;
}
