package pl.piasta.camperoo.common.exception;

import org.springframework.lang.Nullable;

public class BusinessException extends AppException {
    protected BusinessException(String message, ErrorProperty property, @Nullable Object... args) {
        this(message, ErrorCode.ILLEGAL_STATE, property, args);
    }

    protected BusinessException(String message, ErrorCode code, ErrorProperty property, @Nullable Object... args) {
        super(message, code, property, args);
    }
}
