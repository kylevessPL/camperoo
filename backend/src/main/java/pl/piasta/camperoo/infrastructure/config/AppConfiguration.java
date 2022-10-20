package pl.piasta.camperoo.infrastructure.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import pl.piasta.camperoo.util.YamlPropertiesLoader;

import static java.util.Objects.requireNonNull;

@Configuration
class AppConfiguration {

    public static final String APP_CONFIG_PROPERTIES = "app-config.yml";

    @LocalProfile
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class DevConfig {

        public static final String APP_CONFIG_LOCAL_PROPERTIES = "app-config-local.yml";

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            String[] properties = {APP_CONFIG_PROPERTIES, APP_CONFIG_LOCAL_PROPERTIES};
            var configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setProperties(requireNonNull(YamlPropertiesLoader.LAZY.load(properties)));
            return configurer;
        }
    }
}
