package pl.piasta.camperoo.user.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    @Bean
    UserFacade userFacade(
            UserConverter userConverter,
            UserEmailNotifier userEmailNotifier,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserAccountTokenRepository userAccountTokenRepository,
            UserAccountTokenTypeRepository userAccountTokenTypeRepository,
            @Value("${app.user.accountCreationToken.validMinutes}") int accountCreationTokenValidMinutes
    ) {
        var userPasswordManager = new UserAccountManager(
                userRepository,
                roleRepository,
                userAccountTokenRepository,
                userAccountTokenTypeRepository,
                accountCreationTokenValidMinutes
        );
        return new UserFacade(userConverter, userPasswordManager, userEmailNotifier);
    }
}
