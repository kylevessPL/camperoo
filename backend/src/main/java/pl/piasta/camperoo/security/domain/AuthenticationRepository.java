package pl.piasta.camperoo.security.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.user.domain.User;

import java.util.Optional;

public interface AuthenticationRepository extends Repository<User, Long> {
    Optional<User> findByEmail(EmailAddress emailAddress);
}

