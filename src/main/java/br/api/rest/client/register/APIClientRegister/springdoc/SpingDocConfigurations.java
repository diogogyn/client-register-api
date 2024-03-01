package br.api.rest.client.register.APIClientRegister.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpingDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Client Register API")
                        .description("API Rest da aplicação Client Register, contendo as funcionalidades de CRUD de Usuarios")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@client.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://client.com/api/licenca")));
    }
}