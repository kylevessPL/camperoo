package pl.piasta.camperoo.address.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Jacksonized
public class AddressDto {
    @JsonAlias("formatted")
    @NotBlank
    private String address;

    @JsonUnwrapped
    @NotNull
    private CoordinatesDto coordinates;
}
