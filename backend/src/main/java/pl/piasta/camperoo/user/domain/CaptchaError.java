package pl.piasta.camperoo.user.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.exception.ErrorProperty;

import java.util.Map;

import static pl.piasta.camperoo.common.exception.ErrorProperty.CAPTCHA_GENERIC_ERROR;
import static pl.piasta.camperoo.common.exception.ErrorProperty.CAPTCHA_RESPONSE_TOKEN_INVALID;
import static pl.piasta.camperoo.common.exception.ErrorProperty.CAPTCHA_RESPONSE_TOKEN_REVOKED;

@Getter
@RequiredArgsConstructor
public enum CaptchaError {
    INVALID_RESPONSE_TOKEN(
            "The response parameter is invalid or malformed",
            CAPTCHA_RESPONSE_TOKEN_INVALID
    ),
    REVOKED_RESPONSE_TOKEN(
            "The response is no longer valid: either is too old or has been used previously",
            CAPTCHA_RESPONSE_TOKEN_REVOKED
    ),
    GENERIC_ERROR(
            "The response processing completed with errors",
            CAPTCHA_GENERIC_ERROR
    );

    private static final Map<String, CaptchaError> errorCodes = Map.of(
            "invalid-input-response", INVALID_RESPONSE_TOKEN,
            "timeout-or-duplicate", REVOKED_RESPONSE_TOKEN
    );

    private final String message;
    private final ErrorProperty property;

    @JsonCreator
    public static CaptchaError forValue(String value) {
        return errorCodes.getOrDefault(value.toLowerCase(), GENERIC_ERROR);
    }
}
