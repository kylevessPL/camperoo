package pl.piasta.camperoo.common.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.AlphaNumericCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @JsonAlias("formatted")
    @NotBlank
    @AlphaNumericCheck
    private String address;

    @JsonUnwrapped
    @NotNull
    private CoordinatesDto coordinates;
}
