package pl.piasta.camperoo.infrastructure.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.infrastructure.util.RestTemplateFactory;
import pl.piasta.camperoo.user.domain.CaptchaVerificationClient;

@Configuration
class RecaptchaClientConfiguration {
    @Bean
    CaptchaVerificationClient captchaVerificationClient(@Value("${RECAPTCHA_SECRET}") String secret) {
        var requestInterceptor = new RecaptchaRequestInterceptor(secret);
        var restTemplate = RestTemplateFactory.create(requestInterceptor);
        return new RecaptchaVerificationClient(restTemplate);
    }
}
