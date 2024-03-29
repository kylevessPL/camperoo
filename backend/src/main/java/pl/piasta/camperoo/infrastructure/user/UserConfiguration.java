package pl.piasta.camperoo.infrastructure.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    @Bean
    UserDatabaseRepository userRepository(UserJpaRepository jpaRepository) {
        return new UserDatabaseRepository(jpaRepository);
    }

    @Bean
    PersonDatabaseRepository personRepository(PersonJpaRepository jpaRepository) {
        return new PersonDatabaseRepository(jpaRepository);
    }
}
