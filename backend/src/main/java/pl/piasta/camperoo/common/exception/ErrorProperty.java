package pl.piasta.camperoo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorProperty {
    // captcha
    CAPTCHA_RESPONSE_TOKEN_INVALID("captcha.response-token-invalid"),
    CAPTCHA_RESPONSE_TOKEN_REVOKED("captcha.response-token-revoked"),
    CAPTCHA_GENERIC_ERROR("captcha.generic"),

    // verification token
    VERIFICATION_TOKEN_NOT_FOUND("verification-token.not-found"),

    // address
    ROUTE_CALCULATION_ERROR("address.route-calculation-error"),
    COORDINATE_OUT_OF_BOUNDS("address.coordinate-out-of-bounds"),

    // email
    EMAIL_DELIVERY_ERROR("email.delivery-error"),
    EMAIL_REGEX("user.email-regex"),

    // user
    USER_NOT_FOUND("user.not-found"),
    ACCOUNT_DISABLED("user.account-disabled"),
    PASSWORD_LENGTH_MIN("user.password-min"),
    PASSWORD_LENGTH_MAX("user.password-max"),
    WRONG_OLD_PASSWORD("common.wrong-old-password"),
    PASSWORD_UNCHANGED("common.password-unchanged");

    private final String property;
}
