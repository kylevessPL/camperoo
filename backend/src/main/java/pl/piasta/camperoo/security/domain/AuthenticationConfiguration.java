package pl.piasta.camperoo.security.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AuthenticationConfiguration {
    @Bean
    AuthenticationFacade userFacade(
            PasswordEncoder passwordEncoder,
            AuthenticationEmailNotifier authenticationEmailNotifier,
            AuthenticationRepository authenticationRepository,
            VerificationTokenRepository verificationTokenRepository,
            VerificationTokenTypeRepository verificationTokenTypeRepository,
            @Value("${app.security.passwordRecoveryToken.validMinutes}") int passwordRecoveryTokenValidMinutes
    ) {
        var userPasswordManager = new AuthenticationPasswordManager(
                passwordEncoder,
                verificationTokenRepository,
                verificationTokenTypeRepository,
                passwordRecoveryTokenValidMinutes
        );
        return new AuthenticationFacade(authenticationRepository, userPasswordManager, authenticationEmailNotifier);
    }
}
