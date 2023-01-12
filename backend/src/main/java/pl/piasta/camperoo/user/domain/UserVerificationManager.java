package pl.piasta.camperoo.user.domain;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.user.exception.CaptchaVerificationFailureException;

@RequiredArgsConstructor
class UserVerificationManager {
    private final UserCaptchaVerificationClient captchaVerificationClient;

    public void verify(String responseToken, String ip) {
        var result = captchaVerificationClient.verify(responseToken, ip);
        if (!result.isSuccess()) {
            throw new CaptchaVerificationFailureException(result.getError());
        }
    }
}
