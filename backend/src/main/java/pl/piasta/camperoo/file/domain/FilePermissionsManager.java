package pl.piasta.camperoo.file.domain;

public interface FilePermissionsManager {
    boolean canAccess(Long userId, Long fileUuid);
}
