package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
