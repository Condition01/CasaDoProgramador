package br.com.casa.programador.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.casa.programador.enums.TipoPessoa;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//Configuração de autenticação, vai autenticar um usuario
	@Autowired
	MyUserDetailsService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
	
	//Configuração de autorização, vai dizer quais paginas esse usuario pode acessar
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**")
		.hasRole(TipoPessoa.ADMINISTRADOR.toString())
		.antMatchers("/inscrito/cadastro").permitAll()
		.antMatchers("/inscrito/**")
		.hasRole(TipoPessoa.USUARIO.toString())
		.antMatchers("/publicador/**")
		.hasAnyRole(TipoPessoa.PUBLICADOR.toString(), TipoPessoa.ADMINISTRADOR.toString())
		.antMatchers("/imageUpload/**")
		.hasAnyRole(TipoPessoa.PUBLICADOR.toString(), TipoPessoa.ADMINISTRADOR.toString())
		.antMatchers("/editor/**")
		.hasAnyRole(TipoPessoa.PUBLICADOR.toString(), TipoPessoa.ADMINISTRADOR.toString())
		.antMatchers("/").permitAll().and().formLogin().loginPage("/login")
		.usernameParameter("email")
		.passwordParameter("senha");
		
		 http
	      .csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}

}