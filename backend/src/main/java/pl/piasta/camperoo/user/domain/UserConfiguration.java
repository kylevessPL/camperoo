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
            PersonRepository personRepository,
            RoleRepository roleRepository,
            UserTokenRepository userTokenRepository,
            UserTokenTypeRepository userTokenTypeRepository,
            @Value("${app.user.accountCreationToken.validMinutes}") int accountCreationTokenValidMinutes
    ) {
        var userPasswordManager = new UserAccountManager(
                userRepository,
                personRepository,
                roleRepository,
                userTokenRepository,
                userTokenTypeRepository,
                accountCreationTokenValidMinutes
        );
        return new UserFacade(userConverter, userPasswordManager, userEmailNotifier);
    }
}
