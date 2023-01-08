package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;

@RequiredArgsConstructor
public class OrderFacade {
    private final OrderInvoiceManager invoiceManager;

    @PreAuthorize(
            "hasRole('ADMIN') or hasRole('CUSTOMER') and @orderPermissionsManager.canAccess(principal.id, #orderId)")
    @Transactional(readOnly = true)
    public NamedByteArrayResource getInvoice(Long orderId) {
        return invoiceManager.generateInvoice(orderId);
    }
}
