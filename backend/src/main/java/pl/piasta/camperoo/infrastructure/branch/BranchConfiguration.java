package pl.piasta.camperoo.infrastructure.branch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class BranchConfiguration {
    @Bean
    CompanyBranchDatabaseRepository companyBranchRepository(CompanyBranchJpaRepository jpaRepository) {
        return new CompanyBranchDatabaseRepository(jpaRepository);
    }
}
