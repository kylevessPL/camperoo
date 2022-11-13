package pl.piasta.camperoo.common.exception;

import org.springframework.lang.Nullable;

public class DuplicateException extends AppException {
    protected DuplicateException(String message, ErrorCode code, ErrorProperty property, @Nullable Object... args) {
        super(message, code, property, args);
    }
}

