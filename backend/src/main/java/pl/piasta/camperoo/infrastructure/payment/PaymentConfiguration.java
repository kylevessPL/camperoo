package pl.piasta.camperoo.infrastructure.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.order.domain.OrderPaymentDetailsProvider;

@Configuration
class PaymentConfiguration {
    @Bean
    PaymentDatabaseRepository paymentRepository(PaymentJpaRepository jpaRepository) {
        return new PaymentDatabaseRepository(jpaRepository);
    }

    @Bean
    PaymentTypeDatabaseRepository paymentTypeRepository(PaymentTypeJpaRepository jpaRepository) {
        return new PaymentTypeDatabaseRepository(jpaRepository);
    }

    @Bean
    PaymentStatusDatabaseRepository paymentStatusRepository(PaymentStatusJpaRepository jpaRepository) {
        return new PaymentStatusDatabaseRepository(jpaRepository);
    }

    @Bean
    OrderPaymentDetailsProvider paymentDetailsGenerator() {
        return new PaymentDetailsGenerator();
    }
}
