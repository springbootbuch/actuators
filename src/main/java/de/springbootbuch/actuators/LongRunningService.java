package de.springbootbuch.actuators;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class Endpoint {

	private final LongRunningService longRunningService;

	public Endpoint(LongRunningService longRunningService) {
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

	private final CounterService counterService;

	private final GaugeService gaugeService;

	public LongRunningService(
			CounterService counterService, 
			GaugeService gaugeService
	) {
		this.counterService = counterService;
		this.gaugeService = gaugeService;
	}

	public void doStuff() {
		final long sleep = ThreadLocalRandom.current()
				.nextLong(500, 2000);
		try {
			Thread.sleep(sleep);

		} catch (InterruptedException ex) {
		}
		this.gaugeService.submit("longRunningService", sleep);
		this.counterService.increment("longRunningService");
	}
}
