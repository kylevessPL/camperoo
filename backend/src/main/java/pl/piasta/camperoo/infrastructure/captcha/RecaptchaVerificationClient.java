package pl.piasta.camperoo.infrastructure.captcha;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.user.domain.CaptchaVerificationClient;
import pl.piasta.camperoo.user.domain.CaptchaVerificationResult;

@RequiredArgsConstructor
class RecaptchaVerificationClient implements CaptchaVerificationClient {
    protected final RecaptchaSiteVerifyService siteVerifyService;

    @Override
    public CaptchaVerificationResult verify(String responseToken, String ip) {
        return siteVerifyService.verify(responseToken, ip);
    }
}
