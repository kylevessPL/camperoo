package pl.piasta.camperoo.common.exception;

public class ValidationException extends AppException {
    protected ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_ERROR);
    }

    protected ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

