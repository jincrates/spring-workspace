package me.jincrates.bookmanager.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private String error;
    private List<T> data;

    @Builder
    public ResponseDto(String error, List<T> data) {
        this.error = error;
        this.data = data;
    }
}
