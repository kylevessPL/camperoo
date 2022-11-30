package pl.piasta.camperoo.security.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class UserAccountDisabledException extends BusinessException {
    public UserAccountDisabledException(Long id) {
        super(
                "Account of user with id %s is disabled".formatted(id),
                ErrorCode.ACCOUNT_DISABLED, ErrorProperty.ACCOUNT_DISABLED, id
        );
    }
}
