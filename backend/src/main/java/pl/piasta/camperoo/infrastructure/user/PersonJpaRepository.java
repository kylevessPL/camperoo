package pl.piasta.camperoo.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.address.query.AddressProjection;
import pl.piasta.camperoo.user.domain.Person;

import java.util.Optional;

@Repository
interface PersonJpaRepository extends JpaRepository<Person, Long> {
    Optional<AddressProjection> findByUserId(Long userId);
}
