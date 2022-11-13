package pl.piasta.camperoo.common.exception;

public class NotFoundException extends AppException {
    protected NotFoundException(Class<?> objType,
                                String paramName, Object paramValue,
                                ErrorCode code,
                                ErrorProperty property
    ) {
        super(
                String.format(
                        "%s with %s = %s does not exist",
                        objType.getSimpleName(),
                        paramName,
                        paramValue
                ),
                code,
                property,
                paramName,
                paramValue
        );
    }
}
