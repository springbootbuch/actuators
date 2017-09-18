package de.springbootbuch.actuators;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
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
		try {
			this.longRunningService.doStuff();
		} catch (InterruptedException ex) {
		}
		return "Done\n";
	}
}

@Service
public class LongRunningService {

	final BlockingQueue<Integer> someQueue;
	final Timer timer;
	final Counter counter;

	public LongRunningService(
			MeterRegistry registry
	) {
		this.someQueue = registry
			.gauge(
				"longRunningService.gauge",
				new LinkedBlockingQueue<Integer>(),
				LinkedBlockingQueue::size
			);
		this.timer = registry
			.timer("longRunningService.timer");
		this.counter = registry
			.counter("longRunningService.counter");
	}

	public void doStuff() throws InterruptedException {
		final ThreadLocalRandom random = 
			ThreadLocalRandom.current();

		random.ints(10).boxed()
			.forEach(someQueue::offer);
		someQueue.drainTo(
			new ArrayList<>(),
			random.nextInt(someQueue.size())
		);

		final long sleep = random.nextLong(200, 2000);
		Thread.sleep(sleep);
		this.timer.record(sleep, MILLISECONDS);

		this.counter.increment();
	}
}
