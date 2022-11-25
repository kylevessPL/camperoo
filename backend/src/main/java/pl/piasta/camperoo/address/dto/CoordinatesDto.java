package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.Latitude;
import pl.piasta.camperoo.common.validation.Longitude;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @JsonAlias("lat")
    @NotNull(message = "{validation.latitude.null}")
    @Latitude
    private Double latitude;

    @JsonAlias("lon")
    @NotNull(message = "{validation.longitude.null}")
    @Longitude
    private Double longitude;
}
