package pl.piasta.camperoo.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceBetweenCoordinatesQueryDto {
    @Valid
    @NotNull(message = "{validation.coordinates.origin.null}")
    private CoordinatesDto origin;

    @Valid
    @NotNull(message = "{validation.coordinates.destination.null}")
    private CoordinatesDto destination;
}
