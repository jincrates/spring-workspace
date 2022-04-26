package me.jincrates.hr.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "응답 데이터 전송 객체")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    @Schema(description = "에러 메시지")
    private String error;

    @Schema(description = "응답 데이터")
    private List<T> data;
}
