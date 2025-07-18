package com.springms.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryServiceApi() {
        return new OpenAPI()
                .info(new Info().title("Inventory service API").description("/api/inventory API").version("v0.0.1").license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("More info")
                        .url("https://springdoc.org"));
    }
}
