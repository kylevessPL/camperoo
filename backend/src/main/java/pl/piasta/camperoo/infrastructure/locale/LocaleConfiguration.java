package pl.piasta.camperoo.infrastructure.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.common.handler.UserLocaleHandler;

@Configuration
@ComponentScan
class LocaleConfiguration {
    @Bean
    LocaleDatabaseRepository localeRepository(LocaleJpaRepository jpaRepository) {
        return new LocaleDatabaseRepository(jpaRepository);
    }

    @Bean
    UserLocaleHandler userLocaleHandler() {
        return new UserLocaleService();
    }
}
