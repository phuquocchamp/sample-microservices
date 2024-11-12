package com.phuquocchamp.loansservice;

import com.phuquocchamp.loansservice.dto.LoanContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = {LoanContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Loans Microservice REST API Documentation",
                version = "1.0",
                description = "Documentation Loans Service API v1.0",
                contact = @Contact(
                        name = "phuquocchamp",
                        email = "hoangtanphuquoc@gmail.com",
                        url="https://phuquocchamp.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Loans Service Documentation",
                url = "https://phuquocchamp.com"
        )
)
public class LoansServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansServiceApplication.class, args);
    }

}
