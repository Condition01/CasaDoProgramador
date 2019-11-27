package br.com.casa.programador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.casa.programador.controllers.Validador;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CasaDoProgramadorApplication {

	@Bean
	public Validador getValidador() {
		return new Validador();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CasaDoProgramadorApplication.class, args);
	}

}
