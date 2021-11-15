package com.jincrates.club.config;

import com.jincrates.club.security.filter.ApiCheckFilter;
import com.jincrates.club.security.filter.ApiLoginFilter;
import com.jincrates.club.security.handler.ApiLoginFailHandler;
import com.jincrates.club.security.handler.ClubLoginSuccessHandler;
import com.jincrates.club.security.service.ClubUserDetailsService;
import com.jincrates.club.security.util.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsService userDetailsService;  //주입

    @Bean
    PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder: bcrypt라는 해시 함수를 이용해서 패스워드를 암호화하는 목적으로 설계된 클래스
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //permitAll() : 모든 사용자에게 허락한다는 의미로 로그인을 하지 않은 사용자도 익명의 사용자로 간주되어 접근이 가능함
        //http.authorizeRequests()
        //        .antMatchers("/sample/all").permitAll()
        //        .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();  //권한 인증에 문제시 로그인 화면 이동
        http.csrf().disable();  //csrf 토큰 비활성화
        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService); //7dayss

        //필터의 위치 조절
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {

        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authenticationManager());

        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {

        return new ApiCheckFilter("/notes/**/*", jwtUtil());
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}