package pl.piasta.camperoo.infrastructure.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import pl.piasta.camperoo.user.domain.CaptchaVerificationClient;

@Configuration
class CaptchaClientConfiguration {
    @Bean
    CaptchaVerificationClient captchaVerificationClient(@Value("${recaptcha.secret}") String secret) {
        var serviceFactory = HttpServiceProxyFactory.builder(RecaptchaClientAdapterFactory.create(secret)).build();
        var siteVerifyService = serviceFactory.createClient(RecaptchaSiteVerifyService.class);
        return new RecaptchaVerificationClient(siteVerifyService);
    }
}
