package pl.piasta.camperoo.branch.query;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CompanyBranchQueryClient {
    @PreAuthorize("permitAll()")
    List<CompanyBranchProjection> findAllCompanyBranches();
}
