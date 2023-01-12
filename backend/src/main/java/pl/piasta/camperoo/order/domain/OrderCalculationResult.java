package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.discount.domain.Discount;

import java.math.BigDecimal;
import java.util.List;

record OrderCalculationResult(
        Integer days,
        Discount discount,
        CompanyBranch companyBranch,
        Integer distance,
        List<OrderProductCalculation> productCalculations,
        BigDecimal transportationPrice,
        BigDecimal priceSubtotal,
        BigDecimal priceTotal) {
}
