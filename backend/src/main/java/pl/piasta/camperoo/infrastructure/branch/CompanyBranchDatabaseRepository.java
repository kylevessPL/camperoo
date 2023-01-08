package pl.piasta.camperoo.infrastructure.branch;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;
import pl.piasta.camperoo.branch.query.CompanyBranchQueryClient;

import java.util.List;

@RequiredArgsConstructor
class CompanyBranchDatabaseRepository implements CompanyBranchQueryClient {
    private final CompanyBranchJpaRepository repository;

    @Override
    public List<CompanyBranchProjection> findAllCompanyBranches() {
        return repository.findAllBy();
    }
}
