package pl.piasta.camperoo.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.address.dto.AddressesByQueryQueryDto;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.common.dto.AddressDto;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
class AddressController {
    private final AddressGeocodingQueryClient geocodingQueryClient;

    @GetMapping("/search")
    List<AddressDto> getAddressesByQuery(@Valid AddressesByQueryQueryDto query) {
        return geocodingQueryClient.findAllAddressesByQuery(query.getQuery());
    }
}
