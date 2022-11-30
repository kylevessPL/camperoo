package pl.piasta.camperoo.security.domain;

import pl.piasta.camperoo.common.domain.Repository;

public interface VerificationTokenTypeRepository extends Repository<VerificationTokenType, Long> {
    VerificationTokenType getReference(Long id);
}
