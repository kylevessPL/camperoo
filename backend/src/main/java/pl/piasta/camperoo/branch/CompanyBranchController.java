package pl.piasta.camperoo.branch;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.branch.domain.CompanyBranchFacade;
import pl.piasta.camperoo.branch.dto.NearestCompanyBranchDto;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;
import pl.piasta.camperoo.branch.query.CompanyBranchQueryClient;
import pl.piasta.camperoo.common.dto.CoordinatesDto;

import java.util.List;

@RestController
@RequestMapping("/company-branches")
@RequiredArgsConstructor
class CompanyBranchController {
    private final CompanyBranchFacade companyBranchFacade;
    private final CompanyBranchQueryClient companyBranchQueryClient;

    @GetMapping
    List<CompanyBranchProjection> getAllCompanyBranches() {
        return companyBranchQueryClient.findAllCompanyBranches();
    }

    @PostMapping("/nearest")
    NearestCompanyBranchDto getNearestCompanyBranch(@RequestBody @Valid CoordinatesDto dto) {
        return companyBranchFacade.findNearestBranch(dto);
    }
}
