package de.springbootbuch.actuators;

import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig 
		extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(final HttpSecurity http) 
			throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/metrics/counter**")
				.permitAll()
			.antMatchers("/metrics/**")
				.authenticated();
	}
}
