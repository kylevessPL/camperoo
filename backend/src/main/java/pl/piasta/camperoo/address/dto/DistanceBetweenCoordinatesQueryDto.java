package pl.piasta.camperoo.address.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class DistanceBetweenCoordinatesQueryDto {
    @NotNull
    @Valid
    private CoordinatesDto origin;

    @NotNull
    @Valid
    private CoordinatesDto destination;
}
