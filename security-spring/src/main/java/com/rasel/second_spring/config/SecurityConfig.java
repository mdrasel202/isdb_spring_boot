package com.rasel.second_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/public/**").permitAll()
						.requestMatchers("/api/user/**").hasAnyRole("REGULAR_USER", "MANAGER", "ADMIN")
						.requestMatchers("/api/manager/**").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				        .httpBasic(Customizer.withDefaults());

		return http.build();

	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails regularUser = User.builder().username("user").password(passwordEncoder().encode("user123"))
				.roles("REGULAR_USER").build();

		UserDetails manager = User.builder().username("manager").password(passwordEncoder().encode("manager123"))
				.roles("MANAGER").build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin123"))
				.roles("ADMIN").build();

		return new InMemoryUserDetailsManager(regularUser, manager, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
