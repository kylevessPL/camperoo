package pl.piasta.camperoo.security.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.ValueObject;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.ValidationException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Password implements ValueObject {
    public static final int LENGTH_MIN = 12;
    public static final int LENGTH_MAX = 32;

    private String value;

    private Password(String value) {
        this.value = validate(value);
    }

    public static Password of(String value) {
        return new Password(value);
    }

    private String validate(String value) {
        if (value.length() < LENGTH_MIN) {
            throw PasswordValidationException.min();
        }
        if (value.length() > LENGTH_MAX) {
            throw PasswordValidationException.max();
        }
        return value;
    }

    public static class PasswordValidationException extends ValidationException {
        public PasswordValidationException(String message, ErrorProperty property) {
            super(message, property);
        }

        public static PasswordValidationException min() {
            return new PasswordValidationException(
                    "Password must be at least %d characters long".formatted(LENGTH_MIN),
                    ErrorProperty.PASSWORD_LENGTH_MIN
            );
        }

        public static PasswordValidationException max() {
            return new PasswordValidationException(
                    "Password must be at most %d characters long".formatted(LENGTH_MAX),
                    ErrorProperty.PASSWORD_LENGTH_MAX
            );
        }
    }
}

