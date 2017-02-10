package de.springbootbuch.actuators;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		final LocalDate now = LocalDate.now();
		if (Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(now.getDayOfWeek())) {
			return Health.outOfService().withDetail("outOfServiceOn", now.getDayOfWeek()).build();
		}
		return Health.up().build();
	}
}
