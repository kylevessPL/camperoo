package pl.piasta.camperoo.security.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class PasswordUnchangedException extends BusinessException {
    public PasswordUnchangedException() {
        super(
                "New password must be different from the previous password",
                ErrorCode.PASSWORD_UNCHANGED,
                ErrorProperty.PASSWORD_UNCHANGED
        );
    }
}
