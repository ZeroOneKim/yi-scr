package com.yisoccer.yiservice.config.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)  //현재 사용자 검증
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface UserIsCorrect {
}
