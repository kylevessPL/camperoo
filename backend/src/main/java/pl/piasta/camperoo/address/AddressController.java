package pl.piasta.camperoo.address;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.address.dto.AddressDto;
import pl.piasta.camperoo.address.dto.AddressesByQueryQueryDto;
import pl.piasta.camperoo.address.dto.DistanceBetweenCoordinatesQueryDto;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.address.exception.RouteCalculationException;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
class AddressController {

    private final AddressGeocodingQueryClient geocodingQueryClient;

    @GetMapping("/routes:calculate")
    RouteDistanceDto getDistanceBetweenCoordinates(@Valid DistanceBetweenCoordinatesQueryDto query) {
        return geocodingQueryClient.findDistanceBetweenCoordinates(
                query.getOrigin().getLatitude(), query.getOrigin().getLongitude(),
                query.getDestination().getLatitude(), query.getDestination().getLongitude(),
                RouteDistanceDto.class
        ).orElseThrow(RouteCalculationException::new);
    }

    @GetMapping("/search")
    List<AddressDto> getAddressesByQuery(@Valid AddressesByQueryQueryDto query) {
        return geocodingQueryClient.findAllAddressesByQuery(query.getQuery(), AddressDto.class);
    }
}
