package pl.piasta.camperoo.order.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
class OrderConfiguration {
    @Bean
    OrderFacade orderFacade(
            OrderConverter orderConverter,
            OrderEmailNotifier orderEmailNotifier,
            OrderGeocodingClient orderGeocodingClient,
            OrderPaymentDetailsProvider paymentDetailsProvider,
            OrderRepository orderRepository,
            OrderStatusRepository orderStatusRepository,
            OrderUserRepository userRepository,
            OrderProductRepository productRepository,
            OrderDiscountRepository discountRepository,
            OrderCompanyBranchRepository companyBranchRepository,
            OrderDeliveryTypeRepository deliveryTypeRepository,
            OrderFileRepository fileRepository,
            OrderPaymentRepository paymentRepository,
            OrderPaymentTypeRepository paymentTypeRepository,
            OrderPaymentStatusRepository paymentStatusRepository,
            @Value("${app.order.payment.deadlineDays}") long paymentDeadlineDays,
            @Value("#{'${app.delivery.acceptedCountryCodes}'.split(',')}") Set<String> acceptedDeliveryCountryCodes
    ) {
        var orderCalculationManager = new OrderCalculationManager(productRepository);
        var orderCompanyBranchManager = new OrderCompanyBranchManager(
                orderGeocodingClient,
                companyBranchRepository,
                acceptedDeliveryCountryCodes
        );
        var orderInvoiceManager = new OrderInvoiceManager(fileRepository);
        var orderManager = new OrderManager(
                orderInvoiceManager,
                orderEmailNotifier,
                orderCalculationManager,
                orderCompanyBranchManager,
                userRepository,
                orderRepository,
                orderStatusRepository,
                discountRepository,
                productRepository,
                deliveryTypeRepository
        );
        var paymentManager = new OrderPaymentManager(
                paymentDetailsProvider,
                paymentRepository,
                paymentTypeRepository,
                paymentStatusRepository,
                paymentDeadlineDays
        );
        return new OrderFacade(orderConverter, orderEmailNotifier, orderManager, paymentManager);
    }
}
