package pl.piasta.camperoo.infrastructure.verificationtoken;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.security.domain.AuthenticationTokenTypeRepository;
import pl.piasta.camperoo.user.domain.UserTokenTypeRepository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class VerificationTokenTypeDatabaseRepository
        implements AuthenticationTokenTypeRepository, UserTokenTypeRepository {
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
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public VerificationTokenType getReference(Long id) {
        return repository.getReferenceById(id);
    }
}

