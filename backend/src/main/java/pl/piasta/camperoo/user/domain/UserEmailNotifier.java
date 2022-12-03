package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;

public interface UserEmailNotifier {
    void sendAccountCreationToken(VerificationTokenCode token, User user);
}
