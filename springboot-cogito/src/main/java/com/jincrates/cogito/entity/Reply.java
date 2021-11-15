package com.jincrates.cogito.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@ToString(exclude = "board")
@ApiModel(value = "댓글 Entity")
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rno")
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;  //연관관계 지정
}
