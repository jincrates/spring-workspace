package me.jincrates.bookmanager.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import me.jincrates.bookmanager.common.Status;
import me.jincrates.bookmanager.domain.members.Member;
import me.jincrates.bookmanager.domain.members.MemberRole;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class MemberDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해야 합니다.")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, message = "비밀번호는 4자 이상 입력해주세요.")
    private String password;

    @JsonProperty("phone_number")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "010-0000-0000 형식으로 입력해야 합니다.")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public MemberDto(String name, String email, String password, String phoneNumber, MemberRole role, Status status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

    public static Member toEntity(MemberDto dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
    }
}
