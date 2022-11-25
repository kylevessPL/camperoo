package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @JsonAlias("formatted")
    @NotBlank(message = "{validation.address.blank}")
    private String address;

    @JsonUnwrapped
    @NotNull(message = "{validation.coordinates.null}")
    private CoordinatesDto coordinates;
}
