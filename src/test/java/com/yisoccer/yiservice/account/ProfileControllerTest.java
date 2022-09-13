package com.yisoccer.yiservice.account;

import com.yisoccer.yiservice.entity.Account;
import com.yisoccer.yiservice.entity.SignUpForm;
import com.yisoccer.yiservice.repository.AccountRepository;
import com.yisoccer.yiservice.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void beforeEach() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setNickname("Username01");
        signUpForm.setEmail("email.@gmail.com");
        signUpForm.setPassword("pasword135");
        accountService.saveAccount(signUpForm);
    }

    @WithUserDetails(value = "Username01", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("자신의 프로필 입력 - Success")
    @Test
    void EditProfileS() throws Exception {
        String bio = "소개입니다";
        mockMvc.perform(post("/account/profileSetting")
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        Account nickname01 = accountRepository.findByNickname("Username01");
        assertEquals(bio, nickname01.getBio());
    }

}
