package com.aronith.vet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vet - Sistema de Gestión para Veterinarias")
                        .version("1.0")
                        .description("API REST para el control de mascotas, clientes, citas, visitas, recetas y medicamentos")
                        .contact(new Contact()
                                .name("Jhoan Aronith")
                                .email("jhoanmunozman@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

}
