package kln.se.agri.ai.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kln.se.agri")
public class PublicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}

}
