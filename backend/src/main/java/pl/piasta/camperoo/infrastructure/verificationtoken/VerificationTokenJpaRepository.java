package pl.piasta.camperoo.infrastructure.verificationtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.VerificationToken;
import pl.piasta.camperoo.security.domain.VerificationTokenType;

import java.util.Optional;

@Repository
interface VerificationTokenJpaRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByCodeAndType(VerificationTokenCode code, VerificationTokenType type);
}

