package pl.piasta.camperoo.file.domain;

import java.util.UUID;

public interface FilePermissionsManager {
    boolean canAccess(Long userId, UUID fileUuid);
}
