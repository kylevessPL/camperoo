package pl.piasta.camperoo.infrastructure.locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.global.domain.Locale;
import pl.piasta.camperoo.global.query.LocaleProjection;

import java.util.List;

@Repository
public interface LocaleJpaRepository extends JpaRepository<Locale, Long> {
    List<LocaleProjection> findAllBy();
}
