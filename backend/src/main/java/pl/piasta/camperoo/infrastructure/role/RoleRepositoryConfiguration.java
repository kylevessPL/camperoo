package pl.piasta.camperoo.infrastructure.role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class RoleRepositoryConfiguration {
    @Bean
    RoleDatabaseRepository roleRepository(RoleJpaRepository jpaRepository) {
        return new RoleDatabaseRepository(jpaRepository);
    }
}
