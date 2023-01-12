package pl.piasta.camperoo.infrastructure.delivery;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.delivery.domain.DeliveryType;
import pl.piasta.camperoo.delivery.query.DeliveryTypeQueryClient;
import pl.piasta.camperoo.order.domain.OrderDeliveryTypeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class DeliveryTypeDatabaseRepository implements OrderDeliveryTypeRepository, DeliveryTypeQueryClient {
    private final DeliveryTypeJpaRepository repository;

    @Override
    public DeliveryType save(DeliveryType deliveryType) {
        return repository.save(deliveryType);
    }

    @Override
    public DeliveryType getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<DeliveryType> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<DeliveryType> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<ConstantProjection> findAllDeliveryTypes() {
        return repository.findAllBy();
    }
}
