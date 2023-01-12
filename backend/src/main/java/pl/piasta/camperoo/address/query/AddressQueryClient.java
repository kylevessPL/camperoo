package pl.piasta.camperoo.address.query;

import org.springframework.security.access.prepost.PreAuthorize;

public interface AddressQueryClient {
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') and principal.id == #userId")
    AddressProjection findAddressByUserId(Long userId);
}
