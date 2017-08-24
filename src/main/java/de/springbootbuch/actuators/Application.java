package de.springbootbuch.actuators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.opentsdb.OpenTsdbGaugeWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

	@RestController
	@RequestMapping("/hello")
	public static class HelloWorldController {

		@GetMapping
		public String helloWorld(@RequestParam final String name) {
			return "Hello, " + name + "\n";
		}
	}

	@Bean
	@ExportMetricWriter
	MetricWriter metricWriter(MBeanExporter exporter) {
		return new JmxMetricWriter(exporter);
	}

	@Profile("openTsdbEnabled")
	@Bean
	@ExportMetricWriter
	public OpenTsdbGaugeWriter openTsdbGaugeWriter() {
		return new OpenTsdbGaugeWriter();
	}
	
	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
}
