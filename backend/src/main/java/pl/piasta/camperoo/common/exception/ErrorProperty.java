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

    // product
    PRODUCT_NOT_FOUND("product.not-found"),

    // order
    ORDER_NOT_FOUND("order.not-found"),

    // file
    FILE_NOT_FOUND("file.not-found"),

    // user
    USER_NOT_FOUND("user.not-found"),
    ACCOUNT_DISABLED("user.account-disabled"),
    ACCOUNT_ALREADY_EXISTS("user.account-already-exists"),
    ACCOUNT_WITHOUT_ROLE("user.account-without-role"),
    PASSWORD_LENGTH_MIN("user.password-min"),
    PASSWORD_LENGTH_MAX("user.password-max"),
    WRONG_OLD_PASSWORD("user.wrong-old-password"),
    PASSWORD_UNCHANGED("user.password-unchanged"),
    ZIP_CODE_REGEX("user.zip-code-regex"),
    PHONE_NUMBER_REGEX("user.phone-number-regex");

    private final String property;
}
