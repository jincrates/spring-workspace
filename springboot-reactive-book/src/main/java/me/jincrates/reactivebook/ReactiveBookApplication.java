package me.jincrates.reactivebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class ReactiveBookApplication {

	public static void main(String[] args) {
		// 블록하운드: 개발자가 직접 작성한 코드뿐만 아니라 서드파티 라이브러리에 사용된 블로킹 메소드 호출을
		// 모두 찾아내서 알려주는 자바 에이전트다.
		// 스프링 부트 애플리케이션을 시작할 때 블록하운드가 바이트코드를 조작할 수 있게 된다.
		BlockHound.builder()
				.allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
				.install();

		SpringApplication.run(ReactiveBookApplication.class, args);
	}

}
