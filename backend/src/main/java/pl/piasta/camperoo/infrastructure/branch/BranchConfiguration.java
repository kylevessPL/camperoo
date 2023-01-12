package pl.piasta.camperoo.infrastructure.branch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BranchConfiguration {
    @Bean
    CompanyBranchDatabaseRepository companyBranchRepository(CompanyBranchJpaRepository jpaRepository) {
        return new CompanyBranchDatabaseRepository(jpaRepository);
    }
}
