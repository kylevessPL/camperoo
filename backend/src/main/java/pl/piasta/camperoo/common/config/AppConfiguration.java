package pl.piasta.camperoo.common.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import pl.piasta.camperoo.common.util.AppProfiles.LocalProfile;
import pl.piasta.camperoo.common.util.PropertiesLoader;
import ru.maksimvoloshin.utility.YamlResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

import static java.util.Objects.requireNonNull;

@Configuration
class AppConfiguration {
    public static final String APP_CONFIG_PROPERTIES = "app-config.yml";
    public static final String APP_MESSAGES_BASENAME = "app-messages";

    @Bean
    MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    MessageSource messageSource() {
        var messageSource = new YamlResourceBundleMessageSource();
        messageSource.setBasename(APP_MESSAGES_BASENAME);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Bean
    LocalValidatorFactoryBean validator() {
        var localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setValidationMessageSource(messageSource());
        return localValidatorFactory;
    }

    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .failOnUnknownProperties(false)
                .build();
    }

    @LocalProfile
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class DevConfig {
        public static final String APP_CONFIG_LOCAL_PROPERTIES = "app-config-local.yml";

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            String[] properties = {APP_CONFIG_PROPERTIES, APP_CONFIG_LOCAL_PROPERTIES};
            var configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setProperties(requireNonNull(PropertiesLoader.LAZY.load(properties)));
            return configurer;
        }
    }
}
