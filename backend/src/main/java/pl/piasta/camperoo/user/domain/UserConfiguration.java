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
            UserTokenCleanupScheduler userTokenCleanupScheduler,
            UserCaptchaVerificationClient userCaptchaVerificationClient,
            UserUserRepository userRepository,
            UserPersonRepository personRepository,
            UserRoleRepository roleRepository,
            UserTokenRepository userTokenRepository,
            UserTokenTypeRepository userTokenTypeRepository,
            @Value("${app.user.accountCreationToken.validMinutes}") long accountCreationTokenValidMinutes
    ) {
        var userVerificationManager = new UserVerificationManager(userCaptchaVerificationClient);
        var userPasswordManager = new UserAccountManager(
                userTokenCleanupScheduler,
                userRepository,
                personRepository,
                roleRepository,
                userTokenRepository,
                userTokenTypeRepository,
                accountCreationTokenValidMinutes
        );
        return new UserFacade(userConverter, userEmailNotifier, userVerificationManager, userPasswordManager);
    }
}
