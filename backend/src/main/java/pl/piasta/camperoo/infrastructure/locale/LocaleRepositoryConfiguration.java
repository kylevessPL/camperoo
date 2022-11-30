package pl.piasta.camperoo.infrastructure.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class LocaleRepositoryConfiguration {
    @Bean
    LocaleDatabaseRepository localeRepository(LocaleJpaRepository jpaRepository) {
        return new LocaleDatabaseRepository(jpaRepository);
    }
}
