package pl.piasta.camperoo.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.user.domain.Person;

@Repository
interface PersonJpaRepository extends JpaRepository<Person, Long> {
}
