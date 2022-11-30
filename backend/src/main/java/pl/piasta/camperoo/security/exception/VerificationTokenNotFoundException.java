package pl.piasta.camperoo.security.exception;

import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.security.domain.VerificationTokenType;

public class VerificationTokenNotFoundException extends NotFoundException {

    public VerificationTokenNotFoundException(String parameter, Object value, ErrorCode errorCode) {
        super(VerificationTokenType.class, parameter, value, errorCode, ErrorProperty.VERIFICATION_TOKEN_NOT_FOUND);
    }

    public VerificationTokenNotFoundException(Long id, ErrorCode errorCode) {
        this("id", id, errorCode);
    }

    public static VerificationTokenNotFoundException passwordRecovery(VerificationTokenCode token) {
        return new VerificationTokenNotFoundException(
                "code", token.getCode(),
                ErrorCode.PASSWORD_RECOVERY_TOKEN_NOT_FOUND
        );
    }
}
