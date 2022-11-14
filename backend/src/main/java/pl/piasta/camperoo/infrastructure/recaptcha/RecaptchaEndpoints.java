package pl.piasta.camperoo.infrastructure.recaptcha;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RecaptchaEndpoints {
    public static final String SITEVERIFY = "https://www.google.com/recaptcha/api/siteverify";
}
