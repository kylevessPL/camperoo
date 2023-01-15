package pl.piasta.camperoo.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantity;
}
