package pl.piasta.camperoo.common.exception;

public class NotFoundException extends AppException {
    protected NotFoundException(Class<?> objType, String paramName, Object paramValue, ErrorCode errorCode) {
        super(
                String.format(
                        "%s with %s = %s does not exist",
                        objType.getSimpleName(),
                        paramName,
                        paramValue
                ),
                errorCode
        );
    }
}
