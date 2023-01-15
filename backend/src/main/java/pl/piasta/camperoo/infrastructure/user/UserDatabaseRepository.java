package pl.piasta.camperoo.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.common.util.PageBuilder;
import pl.piasta.camperoo.order.domain.OrderUserRepository;
import pl.piasta.camperoo.security.domain.AuthenticationRepository;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.domain.UserUserRepository;
import pl.piasta.camperoo.user.exception.UserNotFoundException;
import pl.piasta.camperoo.user.query.UserBasicProjection;
import pl.piasta.camperoo.user.query.UserProjection;
import pl.piasta.camperoo.user.query.UserQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class UserDatabaseRepository
        implements UserUserRepository, OrderUserRepository, AuthenticationRepository, UserQueryClient {
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
        return repository.existsByEmail(emailAddress);
    }

    @Override
    public Optional<User> findByEmailAddress(EmailAddress emailAddress) {
        return repository.findByEmail(emailAddress);
    }

    @Override
    public Page<UserBasicProjection> findAllUsers(Pageable pageable) {
        var page = repository.findAllIdsBy(pageable);
        var ids = IdProjection.retrieveAllIds(page.toList());
        var content = repository.findAllByIdIn(ids, pageable.getSort());
        return PageBuilder.fromContent(content)
                .page(page)
                .build();
    }

    @Override
    public UserProjection findUserById(Long userId) {
        return repository
                .findOneById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}

