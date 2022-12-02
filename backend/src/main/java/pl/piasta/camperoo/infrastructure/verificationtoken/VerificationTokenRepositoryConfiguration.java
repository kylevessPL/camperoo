package pl.piasta.camperoo.infrastructure.verificationtoken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class VerificationTokenRepositoryConfiguration {
    @Bean
    VerificationTokenDatabaseRepository verificationTokenRepository(VerificationTokenJpaRepository jpaRepository) {
        return new VerificationTokenDatabaseRepository(jpaRepository);
    }

    @Bean
    VerificationTokenTypeDatabaseRepository verificationTokenTypeRepository(
            VerificationTokenTypeJpaRepository jpaRepository) {
        return new VerificationTokenTypeDatabaseRepository(jpaRepository);
    }
}
