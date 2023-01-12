package pl.piasta.camperoo.infrastructure.discount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DiscountConfiguration {
    @Bean
    DiscountDatabaseRepository discountRepository(DiscountJpaRepository jpaRepository) {
        return new DiscountDatabaseRepository(jpaRepository);
    }
}
