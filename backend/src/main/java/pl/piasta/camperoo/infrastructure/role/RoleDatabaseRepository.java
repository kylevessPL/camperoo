package pl.piasta.camperoo.infrastructure.role;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.user.domain.Role;
import pl.piasta.camperoo.user.domain.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class RoleDatabaseRepository implements UserRoleRepository {
    private final RoleJpaRepository repository;

    @Override
    public Role save(Role entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Role> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Role> getAll() {
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
    public Role getReference(Long id) {
        return repository.getReferenceById(id);
    }
}

