package com.yisoccer.yiservice.account;

import com.yisoccer.yiservice.entity.Account;
import com.yisoccer.yiservice.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired  private AccountRepository accountRepository;

    @DisplayName("화면 뷰 테스트")
    @Test
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(model().attributeExists("signUpForm"));
    }

    @DisplayName("가입 처리 -Failed")
    @Test
    void signUpSubmit_failed() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickname", "Username01")
                .param("email", "email@gmail.com")
                .param("password", "1")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(unauthenticated());
    }


    @DisplayName("가입 처리 - Success")
    @Test
    void signUpSubmit_perfect() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("nickname", "Username01")
                .param("email", "email@gmail.com")
                .param("password", "pasword135")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        assertTrue(accountRepository.existsByEmail("email@gmail.com"));
    }


}
