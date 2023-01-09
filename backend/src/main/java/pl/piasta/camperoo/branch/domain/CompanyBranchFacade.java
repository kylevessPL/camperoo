package pl.piasta.camperoo.branch.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.branch.dto.NearestCompanyBranchDto;
import pl.piasta.camperoo.common.domain.vo.Coordinates;
import pl.piasta.camperoo.common.dto.CoordinatesDto;

@RequiredArgsConstructor
@Transactional
public class CompanyBranchFacade {
    private final CompanyBranchRoutingManager companyBranchRoutingManager;

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public NearestCompanyBranchDto findNearestBranch(CoordinatesDto dto) {
        var coordinates = Coordinates.of(dto.getLatitude(), dto.getLongitude());
        var nearestBranchInfo = companyBranchRoutingManager.findNearestBranch(coordinates);
        var branch = nearestBranchInfo.getKey();
        var distance = nearestBranchInfo.getValue();
        return new NearestCompanyBranchDto(
                branch.getName(), branch.getAddress(),
                branch.getPhoneNumber().getPhoneNumber(),
                branch.getEmail().getEmail(), distance
        );
    }
}
