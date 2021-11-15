package me.jincrates.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
	// 서버가 tomcat이 아닌 비동기 방식의 netty가 실행됨
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
