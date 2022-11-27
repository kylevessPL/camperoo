package pl.piasta.camperoo.infrastructure.captcha;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CaptchaParams {
    public static final String SECRET = "secret";
    public static final String RESPONSE_TOKEN = "response";
    public static final String REMOTE_IP = "remoteip";
}
