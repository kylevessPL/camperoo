package pl.piasta.camperoo.address.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Jacksonized
public class DistanceBetweenCoordinatesQueryDto {
    @Valid
    @NotNull
    private CoordinatesDto origin;

    @Valid
    @NotNull
    private CoordinatesDto destination;
}
