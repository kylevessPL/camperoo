package pl.piasta.camperoo.infrastructure.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;

import java.util.List;

@Repository
public interface CompanyBranchJpaRepository extends JpaRepository<CompanyBranch, Long> {
    List<CompanyBranchProjection> findAllBy();
}
