package pl.piasta.camperoo.infrastructure.payment;

import net.datafaker.Faker;
import net.datafaker.providers.base.Finance;
import pl.piasta.camperoo.order.domain.OrderPaymentDetails;
import pl.piasta.camperoo.order.domain.OrderPaymentDetailsProvider;

import static java.util.Locale.forLanguageTag;

class PaymentDetailsGenerator implements OrderPaymentDetailsProvider {
    private static final Finance financeGenerator = new Faker(forLanguageTag("pl")).finance();

    @Override
    public OrderPaymentDetails generateOrderPaymentDetails() {
        String iban = financeGenerator.iban();
        String bic = financeGenerator.bic();
        return new OrderPaymentDetails(iban, bic);
    }
}
