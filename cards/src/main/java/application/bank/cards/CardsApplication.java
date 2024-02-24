package application.bank.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts micro service REST API Documentation",
				description = "Udemy micro services course from EazyBytes REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Mateus Maia",
						email = "mateus@maia.com",
						url = "https://www.mateusmaia.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.mateusmaia.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts micro service REST API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html#/"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
