package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.history.entity.LoginHistory;
import com.zerobase.fastlms.history.service.LoginHistoryService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        LoginHistory loginHistory = LoginHistory.builder()
                .userId(authentication.getName())
                .successDate(LocalDateTime.now())
                .ip(RequestUtils.getClientIpAddr(request))
                .userAgent(request.getHeader("user-agent"))
                .build();

        loginHistoryService.save(loginHistory);

        response.sendRedirect("/");
    }

}
