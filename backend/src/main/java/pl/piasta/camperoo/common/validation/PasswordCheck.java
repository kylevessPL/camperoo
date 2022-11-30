package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.common.validation.PasswordCheck.MESSAGE;
import static pl.piasta.camperoo.security.domain.vo.Password.LENGTH_MAX;
import static pl.piasta.camperoo.security.domain.vo.Password.LENGTH_MIN;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Length(min = LENGTH_MIN, max = LENGTH_MAX, message = MESSAGE)
public @interface PasswordCheck {
    String MESSAGE = "{validation.password.invalid}";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
