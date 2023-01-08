package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.file.domain.FilePermissionsManager;
import pl.piasta.camperoo.infrastructure.order.OrderJpaRepository;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class FileAccessService implements FilePermissionsManager {
    private final OrderJpaRepository repository;

    @Override
    public boolean canAccess(Long userId, Long fileId) {
        var orderUserId = repository.findUserIdByInvoiceId(fileId);
        return isNull(orderUserId) || userId.equals(orderUserId);
    }
}
