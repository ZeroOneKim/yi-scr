package com.yisoccer.yiservice.controller;

import com.yisoccer.yiservice.config.account.UserIsCorrect;
import com.yisoccer.yiservice.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@UserIsCorrect Account account, Model model) {
        if(account != null) {
            model.addAttribute(account);
        }

        return "index";
    }
}
