package pl.piasta.camperoo.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressesByQueryQueryDto {
    @NotBlank(message = "{validation.query.blank}")
    private String query;
}
