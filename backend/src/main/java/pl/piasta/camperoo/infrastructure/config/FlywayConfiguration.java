package pl.piasta.camperoo.infrastructure.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.util.YamlPropertiesLoader;

import javax.sql.DataSource;

@Configuration
class FlywayConfiguration {

    public static final String FLYWAY_CONFIG_PROPERTIES = "flyway-config.yml";

    @Bean(initMethod = "migrate")
    Flyway flyway(final DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .configuration(YamlPropertiesLoader.EAGER.load(FLYWAY_CONFIG_PROPERTIES))
                .load();
    }
}
