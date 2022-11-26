package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.converter.MetersToKilometersConverter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDistanceDto {
    @JsonDeserialize(converter = MetersToKilometersConverter.class)
    @NotNull(message = "{validation.distance.null}")
    @Min(value = 0, message = "{validation.distance.min-0}")
    private Integer distance;

    @JsonAlias("country_code")
    @NotEmpty(message = "{validation.countryCodes.empty}")
    private List<String> countryCodes;
}
