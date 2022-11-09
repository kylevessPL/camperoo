package pl.piasta.camperoo.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.piasta.camperoo.common.util.CookieSupportedLocaleResolver;
import pl.piasta.camperoo.global.domain.LocaleRepository;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
class MvcConfiguration implements WebMvcConfigurer {
    private final LocalValidatorFactoryBean validator;

    @Bean
    LocaleResolver localeResolver(LocaleRepository localeRepository) {
        return new CookieSupportedLocaleResolver(localeRepository);
    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}
