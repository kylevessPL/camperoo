package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.common.domain.vo.EmailAddress.REGEX;
import static pl.piasta.camperoo.common.validation.EmailCheck.MESSAGE;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Email(message = MESSAGE)
@Pattern(regexp = REGEX, message = MESSAGE)
public @interface EmailCheck {
    String MESSAGE = "{validation.email.invalid}";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

