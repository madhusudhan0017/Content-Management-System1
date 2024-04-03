package com.example.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private CustomUserDetailService userDetailService;

	
	public SecurityConfig(CustomUserDetailService userDetailService) {
		super();
		this.userDetailService = userDetailService;
	}

	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12); //we use  bcuz it is widely used & more secured
	}

	
	@Bean
	SecurityFilterChain securityfilterchain (HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) //Cross Side Request Forziry
				.authorizeHttpRequests( auth ->
				auth.requestMatchers("/users/register")
				.permitAll()
				.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailService);
	    
		return provider;
	}

}