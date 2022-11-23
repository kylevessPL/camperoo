package pl.piasta.camperoo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;
import pl.piasta.camperoo.order.domain.OrderFacade;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping(value = "/{id}/invoice", produces = APPLICATION_OCTET_STREAM_VALUE)
    NamedByteArrayResource getInvoice(@PathVariable Long id) {
        return orderFacade.getInvoice(id);
    }
}
