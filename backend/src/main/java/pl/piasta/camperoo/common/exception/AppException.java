package pl.piasta.camperoo.common.exception;

import lombok.Getter;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;

public class AppException extends RuntimeException implements MessageSourceResolvable {
    @Getter
    private final ErrorCode code;
    private final transient ErrorProperty property;
    private final transient Object[] args;

    protected AppException(String message, ErrorCode code, ErrorProperty property, @Nullable Object... args) {
        super(message, null, true, false);
        this.code = code;
        this.property = property;
        this.args = args;
    }

    @Override
    public String[] getCodes() {
        var messageCode = "error." + property.getProperty();
        return new String[]{messageCode};
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public String getDefaultMessage() {
        return getMessage();
    }
}

