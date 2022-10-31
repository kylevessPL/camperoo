package pl.piasta.camperoo.address;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.address.dto.AddressDto;
import pl.piasta.camperoo.address.dto.DistanceBetweenCoordinatesQueryDto;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@Validated
@RequiredArgsConstructor
class GeocodingController {

    private final AddressGeocodingQueryClient queryClient;

    @GetMapping("/routes:calculate")
    RouteDistanceDto getDistanceBetweenCoordinates(@Valid DistanceBetweenCoordinatesQueryDto query) {
        return queryClient.findDistanceBetweenCoordinates(
                query.getOrigin().getLatitude(), query.getOrigin().getLongitude(),
                query.getDestination().getLatitude(), query.getDestination().getLongitude(),
                RouteDistanceDto.class
        ).orElseThrow(RouteCalculationException::new);
    }

    @GetMapping("/search")
    List<AddressDto> getAddressesByQuery(@NotBlank String query) {
        return queryClient.findAllAddressesByQuery(query, AddressDto.class);
    }
}
