package de.springbootbuch.actuators;

import org.springframework.boot.actuate.autoconfigure.security.EndpointRequest;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.health.StatusEndpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Profile("custom-actuator-security")
@Component
public class ActuatorSecurity 
		extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) 
			throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.requestMatchers(
					EndpointRequest.to(
							MetricsEndpoint.class,
							StatusEndpoint.class
					)
				)
				.permitAll()
			.requestMatchers(
					EndpointRequest.toAnyEndpoint()
				)
				.authenticated()
			.antMatchers("/**")
				.authenticated();
	}
}