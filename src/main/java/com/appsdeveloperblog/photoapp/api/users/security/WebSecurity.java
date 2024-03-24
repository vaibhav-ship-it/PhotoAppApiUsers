/**
 * 
 */
package com.appsdeveloperblog.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;

/**
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private Environment environment;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment environment,
			UsersService usersService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.environment = environment;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception	{
		// Configure authentication manager builder
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		http.csrf(AbstractHttpConfigurer::disable);
		// Create Authentication Filter
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, usersService, environment);
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(/*HttpMethod.POST, "/users"*/ new AntPathRequestMatcher("/users", "POST")).permitAll();//.access(new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')"));
			auth.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
		});
		http.addFilter(authenticationFilter)
		.authenticationManager(authenticationManager)
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));		
		http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
		
		return http.build();
	}
}
