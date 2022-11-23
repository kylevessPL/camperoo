package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;

@RequiredArgsConstructor
class OrderInvoiceManager {
    private final OrderRepository orderRepository;

    @SneakyThrows
    public NamedByteArrayResource generateInvoice(Long orderId) {
        var filename = "invoice_%d.pdf".formatted(orderId);
        var order = orderRepository.get(orderId).orElseThrow();
        try (var invoiceGenerator = new OrderInvoiceGenerator(order)) {
            return new NamedByteArrayResource(invoiceGenerator.getRawData(), filename);
        }
    }
}
