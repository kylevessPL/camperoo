package pl.piasta.camperoo.infrastructure.verificationtoken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.security.domain.VerificationTokenRepository;
import pl.piasta.camperoo.security.domain.VerificationTokenTypeRepository;

@Configuration
@ComponentScan
class VerificationTokenRepositoryConfiguration {
    @Bean
    VerificationTokenRepository verificationTokenRepository(VerificationTokenJpaRepository jpaRepository) {
        return new VerificationTokenDatabaseRepository(jpaRepository);
    }

    @Bean
    VerificationTokenTypeRepository verificationTokenTypeRepository(VerificationTokenTypeJpaRepository jpaRepository) {
        return new VerificationTokenTypeDatabaseRepository(jpaRepository);
    }
}
