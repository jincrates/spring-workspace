package me.jincrates.studymanager.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    // 매번 있는지 확인할 수 없으니 테스트 코드로 확인하기 위함
    @DisplayName("회원가입 화면 보이는지 테스트")
    @Test
    void signUpFormTest() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(model().attributeExists("signUpForm"));
    }

    @DisplayName("회원가입 처리 - 입력값 오류")
    @Test
    void signUpSubmitFailTest() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickname", "jincates")
                .param("email", "email..")
                .param("password", "123")
                .with(csrf()))  //이거 안넣으면 테스트 깨진다.
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"));
    }

    @DisplayName("회원가입 처리")
    @Test
    void signUpSubmitTest() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "jincates")
                        .param("email", "jincrates@email.com")
                        .param("password", "12345")
                        .with(csrf()))  //이거 안넣으면 테스트 깨진다.
                        .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/"));

        assertTrue(accountRepository.existsByEmail("jincrates@email.com"));
    }
}
