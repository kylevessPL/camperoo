package pl.piasta.camperoo.infrastructure.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.user.domain.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
}

