package pl.piasta.camperoo.infrastructure.verificationtoken;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.security.domain.VerificationTokenType;
import pl.piasta.camperoo.security.domain.VerificationTokenTypeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class VerificationTokenTypeDatabaseRepository implements VerificationTokenTypeRepository {
    private final VerificationTokenTypeJpaRepository repository;

    @Override
    public VerificationTokenType save(VerificationTokenType entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<VerificationTokenType> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<VerificationTokenType> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VerificationTokenType getReference(Long id) {
        return repository.getReferenceById(id);
    }
}

