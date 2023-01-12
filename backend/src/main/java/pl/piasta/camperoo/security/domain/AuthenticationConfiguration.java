package pl.piasta.camperoo.security.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AuthenticationConfiguration {
    @Bean
    AuthenticationFacade authenticationFacade(
            PasswordEncoder passwordEncoder,
            AuthenticationTokenCleanupScheduler authenticationTokenCleanupScheduler,
            AuthenticationEmailNotifier emailNotifier,
            AuthenticationRepository authenticationRepository,
            AuthenticationTokenRepository authenticationTokenRepository,
            AuthenticationTokenTypeRepository authenticationTokenTypeRepository,
            @Value("${app.security.passwordRecoveryToken.validMinutes}") long passwordRecoveryTokenValidMinutes
    ) {
        var userPasswordManager = new AuthenticationPasswordManager(
                passwordEncoder,
                authenticationTokenCleanupScheduler,
                authenticationTokenRepository,
                authenticationTokenTypeRepository,
                passwordRecoveryTokenValidMinutes
        );
        return new AuthenticationFacade(emailNotifier, userPasswordManager, authenticationRepository);
    }
}
