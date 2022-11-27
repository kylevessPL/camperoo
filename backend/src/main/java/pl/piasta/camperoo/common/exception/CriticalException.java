package pl.piasta.camperoo.common.exception;

import org.springframework.lang.Nullable;

public class CriticalException extends AppException {
    protected CriticalException(String message, ErrorCode code, ErrorProperty property, @Nullable Object... args) {
        super(message, code, property, args);
    }
}
