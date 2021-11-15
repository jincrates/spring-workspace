package com.jincrates.cogito.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @ToString(exclude = "writer")
@ApiModel(value = "게시글 Entity")
public class Board extends BaseEntity{

    @ApiModelProperty(value = "게시글 번호", required = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno")
    private Long bno;

    @ApiModelProperty(value = "게시글 제목", required = true)
    private String title;

    @ApiModelProperty(value = "게시글 내용", required = true)
    private String content;

    /*
    지연로딩
    장점: join을 하지 않기 때문에 단순하게 하나의 테이블을 이용하는 경우에는 빠른 속도의 처리가 가능
    단점: 필요한 순간에 쿼리를 실행해야하기 때문에 연관관계가 복잡한 경우에는 여러 번의 쿼리가 실행됨
    보편적인 코딩 가이드: 지연 로딩을 기본으로 사용하고, 상황에 맞게 필요한 방법을 찾는다.
     */
    @ApiModelProperty(value = "작성자", required = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member writer;  //연관관계 지정

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
