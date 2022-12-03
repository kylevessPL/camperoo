package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

public interface UserTokenTypeRepository extends Repository<VerificationTokenType, Long> {
}
