package pl.piasta.camperoo.user.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findByEmail(EmailAddress emailAddress);
}

