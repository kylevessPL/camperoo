package pl.piasta.camperoo.branch.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.branch.exception.CompanyBranchesNotAvailableException;
import pl.piasta.camperoo.common.domain.vo.Coordinates;

import java.util.Comparator;
import java.util.Set;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
class CompanyBranchRoutingManager {
    private final CompanyBranchRepository companyBranchRepository;
    private final CompanyBranchGeocodingClient companyBranchGeocodingClient;
    private final Set<String> acceptedCountryCodes;

    public Pair<CompanyBranch, Integer> findNearestBranch(Coordinates coordinates) {
        return companyBranchRepository.getAll()
                .stream()
                .map(branch -> mapToBranchDistance(branch, coordinates))
                .filter(pair -> nonNull(pair.getValue()))
                .min(Comparator.comparingInt(Pair::getValue))
                .orElseThrow(CompanyBranchesNotAvailableException::new);
    }

    private Pair<CompanyBranch, Integer> mapToBranchDistance(CompanyBranch companyBranch, Coordinates destination) {
        var origin = Coordinates.of(companyBranch.getLatitude(), companyBranch.getLongitude());
        var distance = calculateDistance(origin, destination);
        return Pair.of(companyBranch, distance);
    }

    private Integer calculateDistance(Coordinates origin, Coordinates destination) {
        var distance = companyBranchGeocodingClient.findDistanceBetweenCoordinates(
                origin.getLatitude(), origin.getLongitude(),
                destination.getLatitude(), destination.getLongitude()
        );
        return distance.filter(e -> acceptedCountryCodes.containsAll(e.getCountryCodes()))
                .map(RouteDistanceDto::getDistance)
                .orElse(null);
    }
}
