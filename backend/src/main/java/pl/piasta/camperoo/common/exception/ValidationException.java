package pl.piasta.camperoo.common.exception;

import org.springframework.lang.Nullable;

public class ValidationException extends AppException {
    protected ValidationException(String message, ErrorProperty property, @Nullable Object... args) {
        this(message, ErrorCode.VALIDATION_ERROR, property, args);
    }

    protected ValidationException(String message, ErrorCode code, ErrorProperty property, @Nullable Object... args) {
        super(message, code, property, args);
    }
}

