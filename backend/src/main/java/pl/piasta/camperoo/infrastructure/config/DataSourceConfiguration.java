package pl.piasta.camperoo.infrastructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.common.util.AppPropertiesLoader;

import javax.sql.DataSource;

@Configuration
class DataSourceConfiguration {

    public static final String DS_CONFIG_PROPERTIES = "ds-config.yml";

    @Bean
    DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    HikariConfig hikariConfig() {
        return new HikariConfig(AppPropertiesLoader.EAGER.load(DS_CONFIG_PROPERTIES));
    }
}
