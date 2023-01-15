package pl.piasta.camperoo.order.domain;

public interface OrderEmailNotifier {
    void sendOrderInvoiceGeneratedEmail(Order order);

    void sendOrderStatusChangeToken(Order order);
}
