package pl.piasta.camperoo.branch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;
import pl.piasta.camperoo.branch.query.CompanyBranchQueryClient;

import java.util.List;

@RestController
@RequestMapping("/company-branches")
@RequiredArgsConstructor
class CompanyBranchController {
    private final CompanyBranchQueryClient companyBranchQueryClient;

    @GetMapping
    List<CompanyBranchProjection> getAllCompanyBranches() {
        return companyBranchQueryClient.findAllCompanyBranches();
    }
}
