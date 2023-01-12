package pl.piasta.camperoo.infrastructure.discount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class DiscountConfiguration {
    @Bean
    DiscountDatabaseRepository discountRepository(DiscountJpaRepository jpaRepository) {
        return new DiscountDatabaseRepository(jpaRepository);
    }
}
