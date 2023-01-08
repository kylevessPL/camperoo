package pl.piasta.camperoo.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.delivery.query.DeliveryTypeQueryClient;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
class DeliveryController {
    private final DeliveryTypeQueryClient deliveryTypeQueryClient;

    @GetMapping("/types")
    List<ConstantProjection> getAllDeliveryTypes() {
        return deliveryTypeQueryClient.findAllDeliveryTypes();
    }
}
