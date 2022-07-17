package me.jincrates.bookmanager.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.bookmanager.common.Status;
import me.jincrates.bookmanager.domain.members.Member;
import me.jincrates.bookmanager.domain.members.MemberRole;
import me.jincrates.bookmanager.web.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApiController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class MemberApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 테스트")
    public void createMemberTest() throws Exception {
        MemberDto dto = MemberDto.builder()
                .name("사용자1")
                .email("user1@email.com")
                .password("1111")
                .phoneNumber("010-1111-2222")
                .role(MemberRole.USER)
                .status(Status.ACTIVE)
                .build();

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(
                    post("/api/members/new")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(
                    status().isOk()
                ).andExpect(
                    jsonPath("$.name").value("사용자1")
                ).andExpect(
                    jsonPath("$.email").value("user1@email.com")
                ).andExpect(
                    jsonPath("$.password").value("1111")
                ).andExpect(
                    jsonPath("$.phone_number").value("010-1111-2222")
                ).andExpect(
                    jsonPath("$.role").value(MemberRole.USER)
                ).andExpect(
                    jsonPath("$.status").value(Status.ACTIVE)
                ).andExpect(
                    status().isOk()
                ).andDo(
                    print()
                );
    }
}