package pl.piasta.camperoo.infrastructure.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class PaymentConfiguration {
    @Bean
    PaymentDatabaseRepository paymentRepository(PaymentJpaRepository jpaRepository) {
        return new PaymentDatabaseRepository(jpaRepository);
    }

    @Bean
    PaymentTypeDatabaseRepository paymentTypeRepository(PaymentTypeJpaRepository jpaRepository) {
        return new PaymentTypeDatabaseRepository(jpaRepository);
    }
}
