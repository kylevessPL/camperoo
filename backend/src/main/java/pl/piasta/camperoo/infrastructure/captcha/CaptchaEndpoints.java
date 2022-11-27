package pl.piasta.camperoo.infrastructure.captcha;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CaptchaEndpoints {
    public static final String SITEVERIFY = "https://www.google.com/recaptcha/api/siteverify";
}
