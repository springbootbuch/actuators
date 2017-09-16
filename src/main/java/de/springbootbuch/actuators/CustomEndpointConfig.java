package de.springbootbuch.actuators;

import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Part of springbootbuch.de.
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ManagementContextConfiguration
public class CustomEndpointConfig {
	
	@Bean
	public CustomEndpoint customEndpoint() {
		return new CustomEndpoint();
	}
}
