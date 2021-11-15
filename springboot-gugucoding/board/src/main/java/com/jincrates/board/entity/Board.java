package com.jincrates.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")  //@ToString은 항상 exclude.
// exclude는 해당 속성값으로 지정된 변수는 toString()에서 제외하기 때문에 지연 로딩을 할 때는 반드시 지정해주는 것이 좋다.
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    //지연 로딩
    //장점: 조인을 하지 않기 때문에 단순하게 하나의 테이블을 이용하는 경우에는 빠른 속도의 처리가 가능하다.
    //단점: 필요한 순간에 쿼리를 실행해야 하기 때문에 연관관계가 복잡한 경우에는 여러 번의 쿼리가 실행된다.
    //보편적인 코딩 가이드 => 지연 로딩을 기본으로 사용하고, 상황에 맞게 필요한 방법을 찾는다.
    @ManyToOne(fetch = FetchType.LAZY)  //명시적으로 Lazy 로딩 지정
    private Member writer;  //연관관계 지정

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
