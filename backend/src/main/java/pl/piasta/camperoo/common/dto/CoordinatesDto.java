package pl.piasta.camperoo.common.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.LatitudeCheck;
import pl.piasta.camperoo.common.validation.LongitudeCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @JsonAlias("lat")
    @NotNull(message = "{validation.latitude.null}")
    @LatitudeCheck
    private Double latitude;

    @JsonAlias("lon")
    @NotNull(message = "{validation.longitude.null}")
    @LongitudeCheck
    private Double longitude;
}
