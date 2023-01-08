package pl.piasta.camperoo.delivery.query;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.query.ConstantProjection;

import java.util.List;

@Transactional(readOnly = true)
public interface DeliveryTypeQueryClient {
    @PreAuthorize("permitAll()")
    List<ConstantProjection> findAllDeliveryTypes();
}
