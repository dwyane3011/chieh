package com.chieh.user.security;

import com.alibaba.fastjson.JSON;
import com.chieh.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Response responseBody = Response.failure("401", exception.getMessage());
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        out.write(JSON.toJSONString(responseBody));
        out.close();
    }
}
