package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.common.domain.vo.Coordinates;
import pl.piasta.camperoo.delivery.domain.DeliveryType;
import pl.piasta.camperoo.order.exception.CompanyBranchesNotAvailableException;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
class OrderCompanyBranchManager {
    private final OrderGeocodingClient geocodingClient;
    private final OrderCompanyBranchRepository companyBranchRepository;
    private final Set<String> acceptedDeliveryCountryCodes;

    public Pair<CompanyBranch, Integer> findNearestCompanyBranch(Long deliveryTypeId, Coordinates coordinates) {
        var isSelfPickup = Objects.equals(deliveryTypeId, DeliveryType.SELF_PICKUP);
        return companyBranchRepository.getAll()
                .stream()
                .map(branch -> mapToBranchDistance(isSelfPickup, branch, coordinates))
                .filter(pair -> nonNull(pair.getValue()))
                .min(Comparator.comparingInt(Pair::getValue))
                .orElseThrow(CompanyBranchesNotAvailableException::new);
    }

    private Pair<CompanyBranch, Integer> mapToBranchDistance(
            boolean selfPickup,
            CompanyBranch companyBranch,
            Coordinates destination
    ) {
        var origin = Coordinates.of(companyBranch.getLatitude(), companyBranch.getLongitude());
        var distance = !selfPickup ? calculateDistance(origin, destination) : 0;
        return Pair.of(companyBranch, distance);
    }

    private Integer calculateDistance(Coordinates origin, Coordinates destination) {
        var distance = geocodingClient.findDistanceBetweenCoordinates(
                origin.getLatitude(), origin.getLongitude(),
                destination.getLatitude(), destination.getLongitude()
        );
        return distance
                .filter(e -> acceptedDeliveryCountryCodes.containsAll(e.getCountryCodes()))
                .map(RouteDistanceDto::getDistance)
                .orElse(null);
    }
}
