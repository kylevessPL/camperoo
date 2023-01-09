package pl.piasta.camperoo.common.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @JsonAlias("formatted")
    private String address;

    @JsonUnwrapped
    private CoordinatesDto coordinates;
}
