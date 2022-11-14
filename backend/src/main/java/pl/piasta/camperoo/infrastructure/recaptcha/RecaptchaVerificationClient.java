package pl.piasta.camperoo.infrastructure.recaptcha;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import pl.piasta.camperoo.infrastructure.util.RestTemplateUtils;
import pl.piasta.camperoo.user.domain.CaptchaVerificationClient;
import pl.piasta.camperoo.user.domain.CaptchaVerificationResult;

import java.util.Map;

@RequiredArgsConstructor
class RecaptchaVerificationClient implements CaptchaVerificationClient {
    protected final RestTemplate client;

    @Override
    public CaptchaVerificationResult verify(String responseToken, String ip) {
        Map<String, Object> params = Map.of(
                RecaptchaParams.RESPONSE_TOKEN, responseToken,
                RecaptchaParams.REMOTE_IP, ip
        );
        var uri = RestTemplateUtils.buildUri(RecaptchaEndpoints.SITEVERIFY, params);
        return client.getForObject(uri, CaptchaVerificationResult.class);
    }
}
