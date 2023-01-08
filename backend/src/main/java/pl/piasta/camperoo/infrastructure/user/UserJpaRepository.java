package pl.piasta.camperoo.infrastructure.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.query.UserBasicProjection;
import pl.piasta.camperoo.user.query.UserProjection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {
    Page<IdProjection> findAllIdsBy(Pageable pageable);

    @EntityGraph("users-graph")
    List<UserBasicProjection> findAllByIdIn(Collection<Long> ids, Sort sort);

    @EntityGraph("users-graph")
    Optional<UserProjection> findOneById(Long id);

    boolean existsByEmail(EmailAddress emailAddress);

    Optional<User> findByEmail(EmailAddress emailAddress);
}

