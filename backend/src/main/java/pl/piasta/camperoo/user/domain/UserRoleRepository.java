package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.Repository;

public interface UserRoleRepository extends Repository<Role, Long> {
    Role getReference(Long id);
}
