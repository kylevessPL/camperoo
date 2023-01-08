package pl.piasta.camperoo.common.exception;

public enum ErrorCode {
    // common
    VALIDATION_ERROR,
    ILLEGAL_STATE,
    CRITICAL,
    DATABASE_ERROR,
    FILE_ERROR,

    // reCAPTCHA
    RECAPTCHA_VALIDATION_ERROR,

    // verification token
    ACCOUNT_CREATION_TOKEN_NOT_FOUND,
    PASSWORD_RECOVERY_TOKEN_NOT_FOUND,
    VERIFICATION_TOKEN_REVOKED,

    // address
    ROUTE_CALCULATION_ERROR,

    // product
    PRODUCT_NOT_FOUND,

    // order
    ORDER_NOT_FOUND,

    // file
    FILE_NOT_FOUND,

    // user
    USER_NOT_FOUND,
    ACCOUNT_DISABLED,
    ACCOUNT_ALREADY_EXISTS,
    WRONG_OLD_PASSWORD,
    PASSWORD_UNCHANGED
}
