package pl.piasta.camperoo.infrastructure.file;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.file.domain.File;
import pl.piasta.camperoo.file.query.FileProjection;

import java.util.Optional;

@Repository
public interface FileJpaRepository extends JpaRepository<File, Long> {
    @EntityGraph("files-graph")
    Optional<FileProjection> findOneById(Long id);
}
