package pl.piasta.camperoo.infrastructure.captcha;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.user.domain.CaptchaVerificationResult;
import pl.piasta.camperoo.user.domain.UserCaptchaVerificationClient;

@RequiredArgsConstructor
class RecaptchaVerificationClient implements UserCaptchaVerificationClient {
    protected final RecaptchaSiteVerifyService siteVerifyService;

    @Override
    public CaptchaVerificationResult verify(String responseToken, String ip) {
        return siteVerifyService.verify(responseToken, ip);
    }
}
