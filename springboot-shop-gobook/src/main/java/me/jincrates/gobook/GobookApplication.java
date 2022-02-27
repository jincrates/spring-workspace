package me.jincrates.gobook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GobookApplication {
	public static void main(String[] args) {
		SpringApplication.run(GobookApplication.class, args);
	}
}
