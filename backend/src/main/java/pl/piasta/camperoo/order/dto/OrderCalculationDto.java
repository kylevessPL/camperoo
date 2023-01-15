package pl.piasta.camperoo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCalculationDto {
    private TransportationCalculationDto transportation;
    private BigDecimal priceSubtotal;
    private BigDecimal priceTotal;
}
