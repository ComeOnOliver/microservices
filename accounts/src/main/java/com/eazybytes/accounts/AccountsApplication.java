package com.eazybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorAwareImpl")
@EnableConfigurationProperties({
	com.eazybytes.accounts.dto.AccountContactDto.class
})
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts API",
		version = "1.0",
		description = "Documentation Accounts API v1.0",
		contact = @Contact(
			name = "Developer",
			email = "aaa@gmail.com"
	),
		license = @io.swagger.v3.oas.annotations.info.License(
			name = "Apache 2.0",
			url = "http://www.apache.org/licenses/LICENSE-2.0.html"
		)
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
