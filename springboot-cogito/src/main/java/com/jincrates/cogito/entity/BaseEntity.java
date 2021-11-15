package com.jincrates.cogito.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//데이터의 등록 시간과 수정 시간은 자동으로 처리할 수 있도록 추상 클래스로 작성
@MappedSuperclass  //@MappedSuperclass이 적용된 클래스는 테이블로 생성되지 않는다.
@EntityListeners(value = {AuditingEntityListener.class})  //@EntityListeners는 JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행
@Getter
public abstract class BaseEntity {

    @CreatedDate  //Entity가 생성되어 저장될 때 시간이 자동 저장
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate  //조회한 Entity의 값을 변경할 때 시간이 자동 저장
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
