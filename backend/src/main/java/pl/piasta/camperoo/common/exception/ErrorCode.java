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
    PASSWORD_RECOVERY_TOKEN_NOT_FOUND,
    VERIFICATION_TOKEN_REVOKED,

    // address
    ROUTE_CALCULATION_ERROR,

    // user
    USER_NOT_FOUND,
    ACCOUNT_DISABLED,
    WRONG_OLD_PASSWORD,
    PASSWORD_UNCHANGED
}
