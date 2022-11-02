package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import pl.piasta.camperoo.common.validation.Latitude;
import pl.piasta.camperoo.common.validation.Longitude;

@Data
@Builder
@Jacksonized
public class CoordinatesDto {
    @JsonAlias("lat")
    @Latitude
    private Double latitude;

    @JsonAlias("lon")
    @Longitude
    private Double longitude;
}
