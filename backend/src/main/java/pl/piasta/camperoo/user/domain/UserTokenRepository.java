package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

import java.util.Optional;

public interface UserTokenRepository extends Repository<VerificationToken, Long> {
    Optional<VerificationToken> findByIdAndType(VerificationTokenCode code, VerificationTokenType type);
}
