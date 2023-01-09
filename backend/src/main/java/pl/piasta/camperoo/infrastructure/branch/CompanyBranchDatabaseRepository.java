package pl.piasta.camperoo.infrastructure.branch;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.branch.domain.CompanyBranchRepository;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;
import pl.piasta.camperoo.branch.query.CompanyBranchQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class CompanyBranchDatabaseRepository implements CompanyBranchRepository, CompanyBranchQueryClient {
    private final CompanyBranchJpaRepository repository;

    @Override
    public List<CompanyBranchProjection> findAllCompanyBranches() {
        return repository.findAllBy();
    }

    @Override
    public CompanyBranch save(CompanyBranch entity) {
        return repository.save(entity);
    }

    @Override
    public CompanyBranch getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<CompanyBranch> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<CompanyBranch> get(Long id) {
        return repository.findById(id);
    }
}
