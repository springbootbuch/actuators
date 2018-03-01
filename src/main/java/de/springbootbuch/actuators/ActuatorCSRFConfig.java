package de.springbootbuch.actuators;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Part of springbootbuch.de.
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Configuration
@Profile("!custom-actuator-security")
public class ActuatorCSRFConfig {

	@Bean
	public WebSecurityConfigurerAdapter csrfDisablingWebSecurityConfigurer() {

		return new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				super.configure(http);
				http.requestMatcher(EndpointRequest.toAnyEndpoint()).csrf().disable();
			}
		};
	}
}
