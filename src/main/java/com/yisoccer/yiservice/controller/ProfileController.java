package com.yisoccer.yiservice.controller;

import com.yisoccer.yiservice.config.account.UserIsCorrect;
import com.yisoccer.yiservice.entity.Account;
import com.yisoccer.yiservice.entity.Profile;
import com.yisoccer.yiservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final AccountService accountService;

    @GetMapping("/account/profileSetting")
    public String editProfilePage(@UserIsCorrect Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new Profile(account));

        return "/account/profileSetting";
    }

    @PostMapping("/account/profileSetting")
    public String editProfile(@UserIsCorrect Account account, @Valid @ModelAttribute Profile profile,
                              Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute(account);
            return "/account/profileSetting";
        }

        accountService.editProfile(account, profile);
        return "redirect:/";
    }
}
