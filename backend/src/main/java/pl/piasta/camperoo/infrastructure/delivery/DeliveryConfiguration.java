package pl.piasta.camperoo.infrastructure.delivery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class DeliveryConfiguration {
    @Bean
    DeliveryTypeDatabaseRepository deliveryTypeRepository(DeliveryTypeJpaRepository jpaRepository) {
        return new DeliveryTypeDatabaseRepository(jpaRepository);
    }
}
