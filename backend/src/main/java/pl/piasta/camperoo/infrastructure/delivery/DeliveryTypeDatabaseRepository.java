package pl.piasta.camperoo.infrastructure.delivery;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.delivery.query.DeliveryTypeQueryClient;

import java.util.List;

@RequiredArgsConstructor
class DeliveryTypeDatabaseRepository implements DeliveryTypeQueryClient {
    private final DeliveryTypeJpaRepository repository;

    @Override
    public List<ConstantProjection> findAllDeliveryTypes() {
        return repository.findAllBy();
    }
}
