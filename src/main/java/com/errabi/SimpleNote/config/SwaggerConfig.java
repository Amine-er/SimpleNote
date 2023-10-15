package com.errabi.SimpleNote.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
   @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title("Simple Note application API").version("1.0.0")
                        .description( "This is the Simple Note application  RESTful service using OpenAPI 3.")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                        .contact(new Contact().email("errabi.ami@gmail.com")));
    }
}
