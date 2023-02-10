package com.chieh.user.security;

import com.alibaba.fastjson.JSON;
import com.chieh.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    private JwtDecoder jwtDecoder;

    public JwtAuthenticationFilter(JwtDecoder jwtDecoder, UserDetailsService userDetailsService) {
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Jwt jwt = jwtDecoder.decode(authorization);
            JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            ChiehUserDetails userDetails =
                    (ChiehUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
            UserHttpServletRequest userHttpServletRequest  = new UserHttpServletRequest(request, userDetails);
            request = userHttpServletRequest;
        } catch (JwtException e) {
            Response responseBody = Response.failure("401", e.getMessage());
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            out.write(JSON.toJSONString(responseBody));
            out.close();
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/login");
    }

}

