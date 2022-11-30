package pl.piasta.camperoo.security.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;

import java.util.Optional;

public interface VerificationTokenRepository extends Repository<VerificationToken, Long> {
    Optional<VerificationToken> findByIdAndType(VerificationTokenCode code, VerificationTokenType type);
}
