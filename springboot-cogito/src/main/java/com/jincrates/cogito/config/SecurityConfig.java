package com.jincrates.cogito.config;

import com.jincrates.cogito.security.handler.LoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //localhost:8080/login
    //id: user
    //pw: Using generated security password: "@"

    @Bean
    PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder: bcrypt라는 해시 함수를 이용해서 패스워드를 암호화하는 목적으로 설계된 클래스
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //permitAll() : 모든 사용자에게 허락한다는 의미로 로그인을 하지 않은 사용자도 익명의 사용자로 간주되어 접근이 가능함

        http.authorizeRequests()
                .antMatchers("/board/*").permitAll()
                .antMatchers("/board/user").hasRole("USER");


        http.formLogin();  //권한 인증에 문제시 로그인 화면 이동
        http.csrf().disable();  //csrf 토큰 비활성화

        http.oauth2Login().successHandler(successHandler());
        //http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService); //7dayss
    }

    @Bean
    public LoginSuccessHandler successHandler() {
        return new LoginSuccessHandler(passwordEncoder());
    }
}
