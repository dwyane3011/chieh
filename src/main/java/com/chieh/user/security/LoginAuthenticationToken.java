package com.chieh.user.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Object credentials;

    private Integer loginType;

    public LoginAuthenticationToken(Object principal, Object credentials, Integer loginType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.loginType = loginType;
        setAuthenticated(false);
    }

    public LoginAuthenticationToken(Object principal, Object credentials,
                                    Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public Integer getLoginType() {
        return loginType;
    }
}
