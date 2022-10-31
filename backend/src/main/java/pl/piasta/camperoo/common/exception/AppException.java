package pl.piasta.camperoo.common.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    protected AppException(String message, ErrorCode errorCode) {
        super(message, null, true, false);
        this.errorCode = errorCode;
    }
}

