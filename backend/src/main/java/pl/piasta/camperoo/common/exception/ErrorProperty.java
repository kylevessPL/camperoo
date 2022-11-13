package pl.piasta.camperoo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorProperty {
    // common
    WRONG_OLD_PASSWORD("wrong-old-password"),
    PASSWORD_UNCHANGED("password-unchanged"),

    // address
    ROUTE_CALCULATION_ERROR("address.route-calculation-error"),
    COORDINATE_OUT_OF_BOUNDS("address.coordinate-out-of-bounds"),

    // user
    USER_NOT_FOUND("user.not-found"),
    EMAIL_INVALID("user.email-regex");

    private final String property;
}
