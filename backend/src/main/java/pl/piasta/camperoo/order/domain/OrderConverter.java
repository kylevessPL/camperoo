package pl.piasta.camperoo.order.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.order.domain.OrderConverter.CompanyBranchConverter;
import pl.piasta.camperoo.order.dto.CalculateOrderDto;
import pl.piasta.camperoo.order.dto.CompanyBranchDto;
import pl.piasta.camperoo.order.dto.OrderCalculationDto;
import pl.piasta.camperoo.order.dto.PlaceOrderDto;
import pl.piasta.camperoo.order.dto.ProductDto;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(uses = CompanyBranchConverter.class)
interface OrderConverter {
    @Mapping(target = "transportation.distance", source = "distance")
    @Mapping(target = "transportation.price", source = "transportationPrice")
    @Mapping(target = "transportation.companyBranch", source = "companyBranch")
    OrderCalculationDto convertToOrderCalculationDto(OrderCalculationResult calculationResult);

    @Mapping(target = "products", source = "products", qualifiedByName = "productsToMap")
    OrderCalculationDetails convertToOrderCalculationDetails(CalculateOrderDto dto);

    @Mapping(target = "products", source = "products", qualifiedByName = "productsToMap")
    OrderCalculationDetails convertToOrderCalculationDetails(PlaceOrderDto dto);

    OrderPlacementDetails convertToOrderPlacementDetails(Long userId, PlaceOrderDto dto);

    @Named("productsToMap")
    static Map<Long, Integer> convertProductsToMap(Collection<ProductDto> dtos) {
        return dtos
                .stream()
                .map(e -> Map.entry(e.getId(), e.getQuantity()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Mapper
    interface CompanyBranchConverter {
        @Mapping(target = "email", source = "email.email")
        @Mapping(target = "phoneNumber", source = "phoneNumber.phoneNumber")
        CompanyBranchDto convertToCompanyBranchDto(CompanyBranch companyBranch);
    }
}
