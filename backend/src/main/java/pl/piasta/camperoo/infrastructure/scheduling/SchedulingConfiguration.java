package pl.piasta.camperoo.infrastructure.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.piasta.camperoo.infrastructure.order.OrderJpaRepository;
import pl.piasta.camperoo.infrastructure.order.OrderStatusJpaRepository;
import pl.piasta.camperoo.infrastructure.payment.PaymentJpaRepository;
import pl.piasta.camperoo.infrastructure.payment.PaymentStatusJpaRepository;

@Configuration
@EnableScheduling
class SchedulingConfiguration {
    @Bean
    SchedulingService schedulingService(
            OrderJpaRepository orderJpaRepository,
            OrderStatusJpaRepository orderStatusJpaRepository,
            PaymentJpaRepository paymentJpaRepository,
            PaymentStatusJpaRepository paymentStatusJpaRepository) {
        var orderJobManager = new OrderJobManager(
                orderJpaRepository, orderStatusJpaRepository,
                paymentJpaRepository, paymentStatusJpaRepository
        );
        return new SchedulingService(orderJobManager);
    }
}
