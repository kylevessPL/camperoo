package pl.piasta.camperoo.infrastructure.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import pl.piasta.camperoo.common.util.LocalProfile;
import pl.piasta.camperoo.common.util.YamlPropertiesLoader;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

@Configuration
class AppConfiguration {

    public static final String APP_CONFIG_PROPERTIES = "app-config.yml";

    @Bean
    public LocaleResolver localeResolver(@Value("${app.locale.default}") String defaultLocale) {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag(defaultLocale));
        localeResolver.setCookieName("locale");
        return localeResolver;
    }

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
