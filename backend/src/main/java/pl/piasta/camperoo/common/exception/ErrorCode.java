package pl.piasta.camperoo.common.exception;

public enum ErrorCode {
    // common
    VALIDATION_ERROR,
    ILLEGAL_STATE,
    CRITICAL,
    DATABASE_ERROR,
    FILE_ERROR,

    // captcha
    CAPTCHA_VALIDATION_ERROR,

    // verification token
    ACCOUNT_CREATION_TOKEN_NOT_FOUND,
    PASSWORD_RECOVERY_TOKEN_NOT_FOUND,

    // branch
    COMPANY_BRANCHES_NOT_AVAILABLE,

    // delivery
    DELIVERY_TYPE_NOT_FOUND,

    // product
    PRODUCT_NOT_FOUND,
    PRODUCT_NOT_AVAILABLE,

    // order
    ORDER_NOT_FOUND,
    ORDER_STATUS_NOT_FOUND,
    ORDER_MISSING_PAYMENT,

    // discount
    DISCOUNT_NOT_FOUND,

    // payment
    PAYMENT_TYPE_NOT_FOUND,

    // file
    FILE_NOT_FOUND,

    // user
    USER_NOT_FOUND,
    ACCOUNT_DISABLED,
    ACCOUNT_STATUS_UNCHANGED,
    ACCOUNT_ALREADY_EXISTS,
    PASSWORD_UNCHANGED
}
