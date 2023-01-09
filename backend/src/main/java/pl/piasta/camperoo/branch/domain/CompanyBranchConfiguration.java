package pl.piasta.camperoo.branch.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
class CompanyBranchConfiguration {
    @Bean
    CompanyBranchFacade companyBranchFacade(
            CompanyBranchRepository branchRepository,
            CompanyBranchGeocodingClient geocodingClient,
            @Value("#{'${app.delivery.acceptedCountryCodes}'.split(',')}") Set<String> acceptedCountryCodes) {
        var routingManager = new CompanyBranchRoutingManager(branchRepository, geocodingClient, acceptedCountryCodes);
        return new CompanyBranchFacade(routingManager);
    }
}
