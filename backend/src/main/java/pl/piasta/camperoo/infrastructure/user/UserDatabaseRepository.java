package pl.piasta.camperoo.infrastructure.user;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.security.domain.AuthenticationRepository;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class UserDatabaseRepository implements UserRepository, AuthenticationRepository {
    private final UserJpaRepository repository;

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAll() {
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
    public boolean existsByEmailAddress(EmailAddress emailAddress) {
        return repository.existsByEmailAddress(emailAddress);
    }

    @Override
    public Optional<User> findByEmailAddress(EmailAddress emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }
}

