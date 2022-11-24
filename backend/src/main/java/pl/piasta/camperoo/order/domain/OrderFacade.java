package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;

@RequiredArgsConstructor
public class OrderFacade {
    private final OrderInvoiceManager invoiceManager;

    @Transactional(readOnly = true)
    public NamedByteArrayResource getInvoice(Long orderId) {
        return invoiceManager.generateInvoice(orderId);
    }
}
