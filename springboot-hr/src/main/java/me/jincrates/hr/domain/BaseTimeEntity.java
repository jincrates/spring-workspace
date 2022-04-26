package me.jincrates.hr.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseTimeEntity {

    @Schema(description = "생성시간")
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Schema(description = "수정시간")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
