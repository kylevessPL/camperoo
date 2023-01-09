package pl.piasta.camperoo.address.query;

import pl.piasta.camperoo.common.dto.AddressDto;

import java.util.List;

public interface AddressGeocodingQueryClient {
    List<AddressDto> findAllAddressesByQuery(String query);
}
