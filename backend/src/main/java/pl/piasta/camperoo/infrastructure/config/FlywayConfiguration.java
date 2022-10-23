package pl.piasta.camperoo.infrastructure.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.util.YamlPropertiesLoader;

import javax.sql.DataSource;

@Configuration
class FlywayConfiguration {

    public static final String FLYWAY_CONFIG_PROPERTIES = "flyway-config.yml";

    @LocalProfile
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class DevConfig {

        public static final String FLYWAY_CONFIG_LOCAL_PROPERTIES = "flyway-config-local.yml";

        @Bean(initMethod = "migrate")
        Flyway flyway(final DataSource dataSource) {
            String[] properties = {FLYWAY_CONFIG_PROPERTIES, FLYWAY_CONFIG_LOCAL_PROPERTIES};
            return Flyway.configure()
                    .dataSource(dataSource)
                    .configuration(YamlPropertiesLoader.EAGER.load(properties))
                    .load();
        }
    }
}
