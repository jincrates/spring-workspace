package com.jincrates.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication: 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야함
//@EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // SpringApplication.run은 내장 WAS를 실행함으로써
        // 항상 서버에 톰캣을 설치할 필요없이 스프링 부트로 만들어진 Jar 파일로 실행하면 됨
        // 내장 WAS를 사용하는 것을 권장 <- 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문
    }

}
