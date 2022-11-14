package pl.piasta.camperoo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorProperty {
    // common
    WRONG_OLD_PASSWORD("common.wrong-old-password"),
    PASSWORD_UNCHANGED("common.password-unchanged"),

    // reCAPTCHA
    CAPTCHA_RESPONSE_TOKEN_INVALID("captcha.response-token-invalid"),
    CAPTCHA_RESPONSE_TOKEN_REVOKED("captcha.response-token-revoked"),
    CAPTCHA_GENERIC_ERROR("captcha.generic"),

    // address
    ROUTE_CALCULATION_ERROR("address.route-calculation-error"),
    COORDINATE_OUT_OF_BOUNDS("address.coordinate-out-of-bounds"),

    // user
    USER_NOT_FOUND("user.not-found"),
    EMAIL_INVALID("user.email-regex");

    private final String property;
}
