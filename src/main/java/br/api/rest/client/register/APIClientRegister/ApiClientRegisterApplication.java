package br.api.rest.client.register.APIClientRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiClientRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientRegisterApplication.class, args);
	}

}
