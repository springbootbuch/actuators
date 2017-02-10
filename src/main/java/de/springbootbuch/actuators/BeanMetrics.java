package de.springbootbuch.actuators;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanMetrics implements PublicMetrics {

	private final ApplicationContext ctx;

	public BeanMetrics(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public Collection<Metric<?>> metrics() {
		final Metric<Integer> numberOfBeans = new Metric<>(
				"beans.number", ctx.getBeanDefinitionCount()
		);
		return Arrays.asList(numberOfBeans);
	}
}
