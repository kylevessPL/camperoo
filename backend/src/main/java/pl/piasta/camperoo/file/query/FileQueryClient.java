package pl.piasta.camperoo.file.query;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface FileQueryClient {
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') and @filePermissionsManager.canAccess(principal.id, #fileId)")
    FileProjection findFileById(Long fileId);
}
