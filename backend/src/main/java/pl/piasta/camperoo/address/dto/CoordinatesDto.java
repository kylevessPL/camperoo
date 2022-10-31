package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import pl.piasta.camperoo.common.validation.Latitude;
import pl.piasta.camperoo.common.validation.Longitude;

@Data
public class CoordinatesDto {
    @JsonAlias("lat")
    @Latitude
    private Double latitude;

    @JsonAlias("lon")
    @Longitude
    private Double longitude;
}
