package pl.piasta.camperoo.infrastructure.recaptcha;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RecaptchaParams {
    public static final String SECRET = "secret";
    public static final String RESPONSE_TOKEN = "response";
    public static final String REMOTE_IP = "remoteip";
}
