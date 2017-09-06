package de.springbootbuch.actuators;

import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.StatusEndpoint;
import org.springframework.boot.autoconfigure.security.SpringBootSecurity;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Profile("custom-actuator-security")
@Component
public class ActuatorSecurity 
		extends WebSecurityConfigurerAdapter {
	
	private SpringBootSecurity springBootSecurity;

	public ActuatorSecurity(
		SpringBootSecurity springBootSecurity
	) {
		this.springBootSecurity = springBootSecurity;
	}
	
	@Override
	protected void configure(final HttpSecurity http) 
			throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.requestMatchers(
					springBootSecurity.endpoints(
							MetricsEndpoint.class,
							StatusEndpoint.class
					)
				)
				.permitAll()
			.requestMatchers(
					springBootSecurity.endpointIds(
							SpringBootSecurity.ALL_ENDPOINTS
					)
				)
				.authenticated()
			.antMatchers("/**")
				.authenticated();
	}
}