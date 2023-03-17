package com.example.demo.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springOpenAPI() {

        // TODO: fix configuration
        return new OpenAPI()
                .info(new Info()
                        .title("online-store")
                        .version("v1")
//                        .version(appProperties.getAPP_VERSION()+"- default")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Service Documentation")
                        .url("https://localhost"));
    }
}
