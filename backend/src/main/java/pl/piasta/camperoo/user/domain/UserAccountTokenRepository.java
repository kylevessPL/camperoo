package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;

public interface UserAccountTokenRepository extends Repository<VerificationToken, Long> {
}
