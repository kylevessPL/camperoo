package pl.piasta.camperoo.user.domain;

public interface UserCaptchaVerificationClient {
    CaptchaVerificationResult verify(String responseToken, String ip);
}
