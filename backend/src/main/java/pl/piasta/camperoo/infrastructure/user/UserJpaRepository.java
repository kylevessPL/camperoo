package pl.piasta.camperoo.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.user.domain.User;

import java.util.Optional;

@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(EmailAddress emailAddress);

    Optional<User> findByEmail(EmailAddress emailAddress);
}

