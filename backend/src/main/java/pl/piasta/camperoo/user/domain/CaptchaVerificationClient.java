package pl.piasta.camperoo.user.domain;

public interface CaptchaVerificationClient {
    CaptchaVerificationResult verify(String responseToken, String ip);
}
