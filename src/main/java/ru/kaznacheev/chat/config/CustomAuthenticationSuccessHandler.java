package ru.kaznacheev.chat.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        this.setDefaultTargetUrl("/chat");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Cookie usernameCookie = new Cookie("USERNAME", userDetails.getUsername());
        Cookie idCookie = new Cookie("USER_ID", String.valueOf(userDetails.getId()));
        usernameCookie.setPath("/");
        idCookie.setPath("/");
        response.addCookie(usernameCookie);
        response.addCookie(idCookie);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
