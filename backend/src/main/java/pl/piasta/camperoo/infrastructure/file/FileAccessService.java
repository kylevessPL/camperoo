package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.file.domain.FilePermissionsManager;
import pl.piasta.camperoo.infrastructure.order.OrderJpaRepository;

import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class FileAccessService implements FilePermissionsManager {
    private final OrderJpaRepository repository;

    @Override
    public boolean canAccess(Long userId, UUID fileUuid) {
        var orderUserId = repository.findUserIdByInvoiceUuid(fileUuid);
        return isNull(orderUserId) || userId.equals(orderUserId);
    }
}
