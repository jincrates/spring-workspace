package com.jincrates.cogito.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "댓글 데이터 전송 객체")
public class ReplyDTO {

    @ApiModelProperty(value = "댓글 번호", required = true)
    private Long rno;

    @ApiModelProperty(value = "댓글 내용", required = true)
    private String text;

    @ApiModelProperty(value = "댓글 작성자", required = true)
    private String replyer;

    @ApiModelProperty(value = "게시글 번호", required = true)
    private Long bno; //게시글 번호

    @ApiModelProperty(value = "등록일자")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime regDate;

    @ApiModelProperty(value = "수정일자")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime modDate;
}
