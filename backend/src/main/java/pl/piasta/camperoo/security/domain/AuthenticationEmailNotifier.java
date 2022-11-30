package pl.piasta.camperoo.security.domain;

import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.user.domain.User;

public interface AuthenticationEmailNotifier {
    void sendPasswordResetToken(VerificationTokenCode token, User user);
}
