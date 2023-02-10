package com.chieh.user.controller;

import com.chieh.entity.Request;
import com.chieh.entity.Response;
import com.chieh.user.entity.request.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Response test(@RequestBody Request request) {
        return Response.success();
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public Response index(@RequestBody Request request) {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(principal);
        return Response.success();
    }
}
