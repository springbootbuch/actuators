package de.springbootbuch.actuators;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.endpoint.Endpoint;
import org.springframework.boot.endpoint.ReadOperation;
import org.springframework.boot.endpoint.Selector;
import org.springframework.boot.endpoint.WriteOperation;
import org.springframework.boot.endpoint.web.WebEndpointResponse;
import org.springframework.http.HttpStatus;

/**
 * Part of springbootbuch.de.
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Endpoint(id = "custom")
public class CustomEndpoint {

	private final AtomicInteger cnt =
			new AtomicInteger();
	
	@ReadOperation
	public String someReadOperation() {
		return "Current value " + cnt.get();
	}
	
	@WriteOperation
	public String someWriteOperation() {
		return "New value " + cnt.incrementAndGet();
	}
	
	@ReadOperation
	public WebEndpointResponse<Void> otherReadOperation(
		@Selector String name
	) {
		return new WebEndpointResponse<>(
				HttpStatus.NOT_IMPLEMENTED.value());
	}	
}
