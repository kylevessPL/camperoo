package pl.piasta.camperoo.infrastructure.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.user.domain.UserRepository;

@Configuration
@ComponentScan
class UserRepositoryConfiguration {
    @Bean
    UserRepository userRepository(UserJpaRepository jpaRepository) {
        return new UserDatabaseRepository(jpaRepository);
    }
}
