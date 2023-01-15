package pl.piasta.camperoo.common.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.LatitudeCheck;
import pl.piasta.camperoo.common.validation.LongitudeCheck;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @JsonAlias("lat")
    @NotNull
    @LatitudeCheck
    private BigDecimal latitude;

    @JsonAlias("lon")
    @NotNull
    @LongitudeCheck
    private BigDecimal longitude;
}
