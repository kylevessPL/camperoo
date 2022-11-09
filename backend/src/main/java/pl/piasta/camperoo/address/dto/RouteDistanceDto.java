package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.converter.MetersToKilometersConverter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
