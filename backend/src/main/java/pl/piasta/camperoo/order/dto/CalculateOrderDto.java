package pl.piasta.camperoo.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.dto.CoordinatesDto;
import pl.piasta.camperoo.common.validation.AlphaNumericCheck;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateOrderDto {
    @NotNull
    private Integer days;

    @Valid
    @NotNull
    private CoordinatesDto destination;

    @NotNull
    private Long deliveryTypeId;

    @Valid
    @NotEmpty
    private Set<ProductDto> products;

    @Size(min = 1, max = 255)
    @AlphaNumericCheck
    private String discountCode;
}
