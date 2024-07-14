package top.chr0nix.maa4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "top.chr0nix.maa4j.repository")
public class Maa4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Maa4jApplication.class, args);
	}

}
