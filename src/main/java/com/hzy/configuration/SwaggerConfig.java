package com.hzy.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hzy
 * @Date: 2020/11/24
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("newsblog-group")
                .packagesToScan("com.hzy")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI publicOpenApi() {
        return new OpenAPI()
                .info(new Info().title("newsblog")
                        .description("list newsblog api \n")
                        .version("v1.0")
                        .contact(new Contact().name("newsblog")));
    }
}
