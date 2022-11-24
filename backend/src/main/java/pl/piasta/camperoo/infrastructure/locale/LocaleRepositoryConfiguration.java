package pl.piasta.camperoo.infrastructure.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.global.domain.LocaleRepository;

@Configuration
@ComponentScan
class LocaleRepositoryConfiguration {
    @Bean
    LocaleRepository localeRepository(LocaleJpaRepository jpaRepository) {
        return new LocaleDatabaseRepository(jpaRepository);
    }
}
