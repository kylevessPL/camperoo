package pl.piasta.camperoo.user.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserQueryClient {
    @PreAuthorize("hasRole('ADMIN')")
    Page<UserBasicProjection> findAllUsers(Pageable pageable);

    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') and principal.id == #userId")
    UserProjection findUserById(Long userId);
}
