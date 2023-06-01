package ru.mimoun.computer_shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = "Решение тестового задания",
                contact = @Contact(url = "https://t.me/MilkBeek", name = "Nikita Seleznev", email = "mimoun136@yandex.ru")
        )
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                             .group("REST API")
                             .pathsToMatch("/api/**")
                             .build();
    }
}
