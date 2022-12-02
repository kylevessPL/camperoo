package pl.piasta.camperoo.address.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.dto.CoordinatesDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceBetweenCoordinatesQueryDto {
    @Valid
    @NotNull
    private CoordinatesDto origin;

    @Valid
    @NotNull
    private CoordinatesDto destination;
}
