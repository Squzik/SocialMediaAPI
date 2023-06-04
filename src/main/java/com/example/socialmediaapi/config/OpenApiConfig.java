package com.example.socialmediaapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        servers = {@Server(url = "/sm", description = "Default Server URL")},
        info = @Info(
                title = "Social Media API",
                version = "1.0",
                description = "Social Media"
        )
)
@SecurityScheme(
        name = OpenApiConfig.SwaggerDependency.SCHEME_NAME,
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenApiConfig {

    @UtilityClass
    public class SwaggerDependency {
        public static final String SCHEME_NAME = "social_media_config";
    }
}
