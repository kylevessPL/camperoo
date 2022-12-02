package pl.piasta.camperoo.security.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

public interface AuthenticationTokenTypeRepository extends Repository<VerificationTokenType, Long> {
}
