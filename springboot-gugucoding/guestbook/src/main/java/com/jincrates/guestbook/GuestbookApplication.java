package com.jincrates.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  //AuditingEntityListener를 활성화 시키기 위해 해당 어노테이션 추가
public class GuestbookApplication {

	public static void main(String[] args) {

		SpringApplication.run(GuestbookApplication.class, args);
	}

}
