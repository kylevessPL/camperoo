package pl.piasta.camperoo.common.exception;

public class BusinessException extends AppException {
    protected BusinessException(String message) {
        super(message, ErrorCode.ILLEGAL_STATE);
    }

    protected BusinessException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
