package pl.piasta.camperoo.infrastructure.verificationtoken;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.AuthenticationTokenRepository;
import pl.piasta.camperoo.user.domain.UserTokenRepository;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;
import pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class VerificationTokenDatabaseRepository implements AuthenticationTokenRepository, UserTokenRepository {
    private final VerificationTokenJpaRepository repository;

    @Override
    public VerificationToken save(VerificationToken entity) {
        return repository.save(entity);
    }

    @Override
    public VerificationToken getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<VerificationToken> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<VerificationToken> getAll() {
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
    public Optional<VerificationToken> findByIdAndType(VerificationTokenCode code, VerificationTokenType type) {
        return repository.findByCodeAndType(code, type);
    }
}

