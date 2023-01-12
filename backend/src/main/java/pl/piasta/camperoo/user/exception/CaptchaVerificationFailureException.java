package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.user.domain.CaptchaError;

public class CaptchaVerificationFailureException extends BusinessException {
    public CaptchaVerificationFailureException(CaptchaError error) {
        super(
                "Captcha verification failure: %s".formatted(error.getMessage()),
                ErrorCode.CAPTCHA_VALIDATION_ERROR, error.getProperty()
        );
    }
}
