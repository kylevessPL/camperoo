package pl.piasta.camperoo.infrastructure.payment;

import com.github.javafaker.Faker;
import com.github.javafaker.Finance;
import pl.piasta.camperoo.order.domain.OrderPaymentDetails;
import pl.piasta.camperoo.order.domain.OrderPaymentDetailsProvider;

class PaymentDetailsGenerator implements OrderPaymentDetailsProvider {
    private static final Finance financeGenerator = Faker.instance().finance();

    @Override
    public OrderPaymentDetails generateOrderPaymentDetails() {
        String iban = financeGenerator.iban("pl");
        String bic = financeGenerator.bic();
        return new OrderPaymentDetails(iban, bic);
    }
}
