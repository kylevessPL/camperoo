package pl.piasta.camperoo.infrastructure.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.common.util.PropertiesLoader;

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
        return new HikariConfig(PropertiesLoader.EAGER.load(DS_CONFIG_PROPERTIES));
    }
}
