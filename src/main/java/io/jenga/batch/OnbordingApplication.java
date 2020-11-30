package io.jenga.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnbordingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnbordingApplication.class, args);
	}

}
