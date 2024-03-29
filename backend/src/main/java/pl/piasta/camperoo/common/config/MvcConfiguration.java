package pl.piasta.camperoo.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.piasta.camperoo.global.domain.LocaleRepository;

import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
class MvcConfiguration implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;
    private final LocalValidatorFactoryBean validator;

    @Bean
    LocaleResolver localeResolver(LocaleRepository localeRepository) {
        return new CookieSupportedLocaleResolver(localeRepository);
    }

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageableHandlerMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        converters.add(new ResourceHttpMessageConverter());
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}
