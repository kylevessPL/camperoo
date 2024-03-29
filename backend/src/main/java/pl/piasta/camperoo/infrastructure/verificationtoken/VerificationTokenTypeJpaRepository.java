package pl.piasta.camperoo.infrastructure.verificationtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

@Repository
interface VerificationTokenTypeJpaRepository extends JpaRepository<VerificationTokenType, Long> {
}
