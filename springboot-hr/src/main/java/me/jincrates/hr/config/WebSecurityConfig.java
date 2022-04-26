package me.jincrates.hr.config;

import lombok.RequiredArgsConstructor;
import me.jincrates.hr.config.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http.cors() // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정.
            .and()
            .csrf().disable() // csrf는 현재 사용하지 않으므로 disable
            .httpBasic().disable() // token을 사용하므로 basic 인증 disable
            .sessionManagement()  // session 기반이 아님을 선언
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.authorizeRequests() // /와 /auth/** 경로는 인증 안해도 됨.
            .antMatchers("/", "/h2-console/**", "/v2/api-docs", "/swagger**/**", "/api/auth/**").permitAll()
            .anyRequest().authenticated() // /와 /auth/**이외의 모든 경로는 인증 해야됨.
        ;

        //h2-console iframe 오류 때문에 설정
        http.headers().frameOptions().sameOrigin();

        // filter 등록.
        // 매 리퀘스트마다
        // CorsFilter 실행한 후에
        // jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}