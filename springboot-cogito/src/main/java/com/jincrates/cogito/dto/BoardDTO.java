package com.jincrates.cogito.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "게시글 데이터 전송 객체")
public class BoardDTO {

    @ApiModelProperty(value = "게시글 번호", required = true)
    private Long bno;

    @ApiModelProperty(value = "게시글 제목", required = true)
    private String title;

    @ApiModelProperty(value = "게시글 내용")
    private String content;

    @ApiModelProperty(value = "작성자 이메일(id)",  required = true)  //필수여부
    private String writerEmail;

    @ApiModelProperty(value = "작성자 이름")
    private String writerName;

    @ApiModelProperty(value = "등록일자")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime regDate;

    @ApiModelProperty(value = "수정일자")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime modDate;

    @ApiModelProperty(value = "해당 게시글의 댓글 수")
    private int replyCount;
}
