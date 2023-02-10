package com.chieh.user.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ChiehUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("扩展Provider");
        LoginAuthenticationToken authenticationRequest = (LoginAuthenticationToken) authentication;
        String principal = (String) authenticationRequest.getPrincipal();
        String credentials = (String) authenticationRequest.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        if (LoginType.USERNAME_PASSORD == authenticationRequest.getLoginType()) {
            if(Objects.isNull(userDetails)){
                throw new AuthenticationServiceException("账号或密码不正确");
            }
            if(!userDetails.getPassword().equals(credentials)){
                throw new AuthenticationServiceException("账号或密码不正确");
            }
        } else if (LoginType.SMS == authenticationRequest.getLoginType()) {
            if (!credentials.equals("123456")) {
                throw new AuthenticationServiceException("短信验证码不正确");
            }
        }
        LoginAuthenticationToken authenticationResponse = new LoginAuthenticationToken(principal, null,
                userDetails.getAuthorities());
        authenticationResponse.setDetails(authentication.getDetails());
        return authenticationResponse;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
