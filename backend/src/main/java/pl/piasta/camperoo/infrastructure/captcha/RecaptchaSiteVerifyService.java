package pl.piasta.camperoo.infrastructure.captcha;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import pl.piasta.camperoo.user.domain.CaptchaVerificationResult;

import static pl.piasta.camperoo.infrastructure.captcha.CaptchaParams.REMOTE_IP;
import static pl.piasta.camperoo.infrastructure.captcha.CaptchaParams.RESPONSE_TOKEN;

@HttpExchange(CaptchaEndpoints.SITEVERIFY)
interface RecaptchaSiteVerifyService {
    @GetExchange
    CaptchaVerificationResult verify(@RequestParam(RESPONSE_TOKEN) String token, @RequestParam(REMOTE_IP) String ip);
}
