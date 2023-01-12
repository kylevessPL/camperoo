package pl.piasta.camperoo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportationCalculationDto {
    private CompanyBranchDto companyBranch;
    private Integer distance;
    private BigDecimal price;
}
