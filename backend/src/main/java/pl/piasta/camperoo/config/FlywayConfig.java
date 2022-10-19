package pl.piasta.camperoo.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.util.YamlPropertiesLoader;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    public static final String FLYWAY_CONFIG_PROPERTIES = "flyway-config.yml";

    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .configuration(YamlPropertiesLoader.EAGER.load(FLYWAY_CONFIG_PROPERTIES))
                .load();
    }
}
