package com.chieh.user.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if (!MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            throw new AuthenticationServiceException("Authentication MediaType not supported: " + request.getContentType());
        }

        String requestBodyText = null;
        try {
            requestBodyText = IOUtils.readInputStreamToString(request.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String principal;
        String credentials;
        JSONObject requestBodyJson = JSON.parseObject(requestBodyText);
        Integer loginType = requestBodyJson.getInteger("type");
        if(Objects.isNull(loginType) || loginType.equals(LoginType.USERNAME_PASSORD)){
            // 默认账号密码登录
            principal = requestBodyJson.getString("username");
            credentials = requestBodyJson.getString("password");
        } else if(loginType.equals(LoginType.SMS)){
            principal = requestBodyJson.getString("username");
            credentials = requestBodyJson.getString("code");
        } else {
            throw new AuthenticationServiceException("Authentication login type not supported: " + loginType);
        }
        LoginAuthenticationToken authentication = new LoginAuthenticationToken(principal, credentials, loginType);
        authentication.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authentication);
    }
}
