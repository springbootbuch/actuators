package de.springbootbuch.actuators;

import java.util.Collection;
import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.boot.actuate.metrics.jdbc.DataSourcePoolMetrics;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Configuration
public class DataSourceMetricsConfig {

	@Bean
	public DataSourcePoolMetrics dataSourcePoolMetrics(
		DataSource dataSource,
		Collection<DataSourcePoolMetadataProvider> 
				metadataProviders
	) {
		return new DataSourcePoolMetrics(
				dataSource, metadataProviders, "data.source",
				Collections.emptyList()
		);
	}

}
