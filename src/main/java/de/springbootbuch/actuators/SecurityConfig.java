package de.springbootbuch.actuators;

import org.springframework.boot.actuate.autoconfigure.web.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("custom-actuator-security")
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
			.antMatchers("/application/metrics/counter**")
				.permitAll()
			.antMatchers("/application/**")
				.authenticated();
	}
}
