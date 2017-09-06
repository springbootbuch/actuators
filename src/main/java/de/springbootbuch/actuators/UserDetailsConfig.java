package de.springbootbuch.actuators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Part of springbootbuch.de.
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Configuration
public class UserDetailsConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		final InMemoryUserDetailsManager manager
				= new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("test")
						.password("test")
						.roles("USER", "ACTUATOR")
						.build()
		);
		return manager;
	}
}
