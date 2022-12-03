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
public class ZipCode implements ValueObject {
    public static final String REGEX = "^[0-9]{2}-[0-9]{3}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private String zipCode;

    private ZipCode(String zipCode) {
        this.zipCode = validate(zipCode.trim());
    }

    public static ZipCode of(String zipCode) {
        return new ZipCode(zipCode);
    }

    private String validate(String zipCode) {
        if (!PATTERN.matcher(zipCode).matches()) {
            throw ZipCodeValidationException.regex();
        }
        return zipCode;
    }

    @Override
    public String toString() {
        return zipCode;
    }

    private static class ZipCodeValidationException extends ValidationException {
        private ZipCodeValidationException(String message, ErrorProperty property) {
            super(message, property);
        }

        public static ZipCodeValidationException regex() {
            return new ZipCodeValidationException(
                    "Zip code does not match regex pattern",
                    ErrorProperty.ZIP_CODE_REGEX
            );
        }
    }
}
