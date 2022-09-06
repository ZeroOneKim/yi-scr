package com.yisoccer.yiservice.controller;

import com.yisoccer.yiservice.entity.Account;
import com.yisoccer.yiservice.entity.SignUpForm;
import com.yisoccer.yiservice.repository.AccountRepository;
import com.yisoccer.yiservice.service.AccountService;
import com.yisoccer.yiservice.service.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    @InitBinder("signup")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up";
    }
    @PostMapping("sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if(errors.hasErrors()) {
            return "account/sign-up";
        }
        accountService.saveAccount(signUpForm);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}