package pl.piasta.camperoo.verificationtoken.exception;

import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

public class VerificationTokenNotFoundException extends NotFoundException {
    private VerificationTokenNotFoundException(VerificationTokenCode token, ErrorCode code) {
        super(VerificationTokenType.class, "code", token.getCode(), code, ErrorProperty.VERIFICATION_TOKEN_NOT_FOUND);
    }

    public static VerificationTokenNotFoundException accountCreation(VerificationTokenCode token) {
        return new VerificationTokenNotFoundException(token, ErrorCode.ACCOUNT_CREATION_TOKEN_NOT_FOUND);
    }

    public static VerificationTokenNotFoundException passwordRecovery(VerificationTokenCode token) {
        return new VerificationTokenNotFoundException(token, ErrorCode.PASSWORD_RECOVERY_TOKEN_NOT_FOUND);
    }
}
