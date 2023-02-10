/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chieh.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    @Autowired
    private LoginAuthenticationProvider smsAuthenticationProvider;

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   LoginAuthenticationFilter loginAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/login", "/test").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(smsAuthenticationProvider)
                .addFilterAt(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, LoginAuthenticationFilter.class)
                // 不disable的情况下，接口用非浏览器调用的方式请求不通
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(AuthenticationManager authenticationManager) {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(authenticationManager);
        loginAuthenticationFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
        return loginAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtDecoder jwtDecoder, UserDetailsService userDetailsService){
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtDecoder, userDetailsService);
        return jwtAuthenticationFilter;
    }
}