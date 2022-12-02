package pl.piasta.camperoo.user.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.ValueObject;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.ValidationException;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class PhoneNumber implements ValueObject {
    public static final String REGEX = "^(?:1[2-8]|2[2-69]|3[2-49]|4[1-8]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\\d{7}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = validate(phoneNumber.trim());
    }

    public static PhoneNumber of(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    private String validate(String phoneNumber) {
        if (!PATTERN.matcher(phoneNumber).matches()) {
            throw PhoneNumberValidationException.regex();
        }
        return phoneNumber;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    private static class PhoneNumberValidationException extends ValidationException {
        private PhoneNumberValidationException(String message, ErrorProperty property) {
            super(message, property);
        }

        public static PhoneNumberValidationException regex() {
            return new PhoneNumberValidationException(
                    "Phone number does not match regex pattern",
                    ErrorProperty.PHONE_NUMBER_REGEX
            );
        }
    }
}
