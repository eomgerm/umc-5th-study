package com.umc5th.study.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

public class SwaggerConfig {

    @Bean()
    public OpenAPI setUpSwagger() {
        SecurityScheme bearerSecurityScheme = new SecurityScheme().type(Type.HTTP)
                                                                  .scheme("Bearer")
                                                                  .bearerFormat("Authorization")
                                                                  .in(In.HEADER)
                                                                  .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("Authorization");

        Components components = new Components().addSecuritySchemes("Authorization", bearerSecurityScheme);

        return new OpenAPI().components(components)
                            .addSecurityItem(securityRequirement)
                            .info(new Info().title("Study API")
                                            .description("UMC 5th Study API Specification"));
    }
}
