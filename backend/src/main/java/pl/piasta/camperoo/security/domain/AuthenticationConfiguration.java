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
            AuthenticationEmailNotifier authenticationEmailNotifier,
            AuthenticationRepository authenticationRepository,
            AuthenticationTokenRepository authenticationTokenRepository,
            AuthenticationTokenTypeRepository authenticationTokenTypeRepository,
            @Value("${app.security.passwordRecoveryToken.validMinutes}") int passwordRecoveryTokenValidMinutes
    ) {
        var userPasswordManager = new AuthenticationPasswordManager(
                passwordEncoder,
                authenticationTokenRepository,
                authenticationTokenTypeRepository,
                passwordRecoveryTokenValidMinutes
        );
        return new AuthenticationFacade(authenticationRepository, userPasswordManager, authenticationEmailNotifier);
    }
}
