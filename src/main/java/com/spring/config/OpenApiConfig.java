package com.spring.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    private static final String SECURITY_SCHEMA_NAME = "Bearer oAuth Token";

    @Bean
    public OpenAPI customopenAPI(@Value("${application-description}") String appDescription,
                                 @Value("application-version") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Sample Application API")
                        .version(appVersion)
                        .contact(getContact())
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(getLicense()))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEMA_NAME, Arrays.asList("read", "write")))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEMA_NAME, new SecurityScheme().name(SECURITY_SCHEMA_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("ahmedelsheikh@gmail.com");
        contact.setName("Book Service");
        contact.setUrl("https://www.book.com");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }

    private License getLicense() {
        License license = new License();
        license.setName("Apache License, Version 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
        license.setExtensions(Collections.emptyMap());

        return license;
    }

    //    Start Cateegories swagger-ui
    @Bean
    public GroupedOpenApi userManagementApi() {
        String packagesToscan[] = {"com.service.usermanagement"};
        return GroupedOpenApi.builder()
                .group("User Management API")
                .packagesToScan(packagesToscan)
                .addOperationCustomizer(appTokenHeaderParam())
                .build();
    }

    @Bean
    public GroupedOpenApi setupApi() {
        String packagesToscan[] = {"com.spring"};
        return GroupedOpenApi.builder()
                .group("Book API")
                .packagesToScan(packagesToscan)
                .addOperationCustomizer(appTokenHeaderParam())
                .build();
    }

    @Bean
    public OperationCustomizer appTokenHeaderParam() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter headerParameter = new Parameter().in(ParameterIn.HEADER.toString()).required(false).
                    schema(new StringSchema()._default("app_token_header_default_value")).name("app_token_header").description("App Token Header");
            operation.addParametersItem(headerParameter);
            return operation;
        };
    }
//    End Categories swagger-ui
}
