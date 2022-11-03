package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import pl.piasta.camperoo.common.converter.MetersToKilometersConverter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Jacksonized
public class RouteDistanceDto {
    @JsonDeserialize(converter = MetersToKilometersConverter.class)
    @NotNull
    @Min(0)
    private Integer distance;

    @JsonAlias("country_code")
    @NotEmpty
    private List<String> countryCodes;
}
