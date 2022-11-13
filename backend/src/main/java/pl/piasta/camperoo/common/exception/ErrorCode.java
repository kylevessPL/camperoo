package pl.piasta.camperoo.common.exception;

public enum ErrorCode {
    // common
    VALIDATION_ERROR,
    ILLEGAL_STATE,
    CRITICAL,
    DATABASE_ERROR,
    WRONG_OLD_PASSWORD,
    PASSWORD_UNCHANGED,
    FILE_ERROR,

    // address
    ROUTE_CALCULATION_ERROR,

    // user
    USER_NOT_FOUND
}
