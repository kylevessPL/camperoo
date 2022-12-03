package pl.piasta.camperoo.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.ValidationException;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class EmailAddress implements ValueObject {
    public static final String REGEX = "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private String email;

    private EmailAddress(String email) {
        this.email = validate(email.toLowerCase().trim());
    }

    public static EmailAddress of(String email) {
        return new EmailAddress(email);
    }

    private String validate(String address) {
        if (!PATTERN.matcher(address).matches()) {
            throw EmailValidationException.regex();
        }
        return address;
    }

    @Override
    public String toString() {
        return email;
    }

    private static class EmailValidationException extends ValidationException {
        private EmailValidationException(String message, ErrorProperty property) {
            super(message, property);
        }

        public static EmailValidationException regex() {
            return new EmailValidationException(
                    "Email address does not match regex pattern",
                    ErrorProperty.EMAIL_REGEX
            );
        }
    }
}
