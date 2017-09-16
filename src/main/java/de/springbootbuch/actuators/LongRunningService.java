package de.springbootbuch.actuators;

import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class WebEndpoint {

	private final LongRunningService longRunningService;

	public WebEndpoint(LongRunningService longRunningService) {
		this.longRunningService = longRunningService;
	}

	@GetMapping("/compute")
	@ResponseBody
	public String compute() {
		this.longRunningService.doStuff();
		return "Done\n";
	}
}

@Service
public class LongRunningService {

	private final MeterRegistry meterRegistry;

	public LongRunningService(
			MeterRegistry meterRegistry
	) {
		this.meterRegistry = meterRegistry;
	}

	public void doStuff() {
		final long sleep = ThreadLocalRandom.current()
				.nextLong(500, 2000);
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException ex) {
		}
		this.meterRegistry
			.gauge("longRunningService.gauge", sleep);
		this.meterRegistry
			.counter("longRunningService.counter")
			.increment();
	}
}
