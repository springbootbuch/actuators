package de.springbootbuch.actuators;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpoint 
		extends AbstractEndpoint<String> {

	public CustomEndpoint() {
		super("custom", false, true);
	}

	@Override
	public String invoke() {
		return "The answer to everything "
				+ "can be found under /info\n";
	}
}
