package pl.piasta.camperoo.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressesByQueryQueryDto {
    @NotBlank(message = "{validation.query.blank}")
    private String query;
}
