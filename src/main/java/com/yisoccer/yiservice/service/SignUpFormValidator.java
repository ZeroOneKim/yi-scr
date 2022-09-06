package com.yisoccer.yiservice.service;

import com.yisoccer.yiservice.entity.SignUpForm;
import com.yisoccer.yiservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {
    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o , Errors errors) {
        SignUpForm signup = (SignUpForm)o;
        if(accountRepository.existsByEmail(signup.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signup.getEmail()},
                    "이미 사용중인 이메일 입니다.");
        }
        if(accountRepository.existsByNickname(signup.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signup.getNickname()},
                    "이미 사용중인 닉네임 입니다.");
        }
    }
}
