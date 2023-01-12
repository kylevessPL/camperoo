package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.lang.NonNull;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.discount.domain.Discount;
import pl.piasta.camperoo.order.exception.ProductNotAvailableException;
import pl.piasta.camperoo.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RequiredArgsConstructor
class OrderCalculationManager {
    private final OrderProductRepository productRepository;

    public OrderCalculationResult calculateOrderPrice(
            Map<Product, Integer> products,
            Pair<CompanyBranch, Integer> companyBranch,
            Discount discount
    ) {
        var productsPrice = calculateProductsPrice(products);
        var transportationPrice = calculateTransportationTotalPrice(companyBranch.getValue());
        var finalPrice = calculateFinalPrice(productsPrice.getValue(), transportationPrice, discount);
        return new OrderCalculationResult(
                discount,
                companyBranch.getLeft(),
                companyBranch.getRight(),
                productsPrice.getLeft(),
                transportationPrice,
                finalPrice.getLeft(),
                finalPrice.getRight()
        );
    }

    private Pair<List<ProductPriceCalculation>, BigDecimal> calculateProductsPrice(Map<Product, Integer> products) {
        List<ProductPriceCalculation> prices = new ArrayList<>();
        var totalPrice = products.entrySet()
                .stream()
                .map(e -> calculateProductPrice(prices, e))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return Pair.of(prices, totalPrice);
    }

    private BigDecimal calculateTransportationTotalPrice(Integer distance) {
        var transportation = productRepository.getTransportationProduct();
        return transportation.getPrice().multiply(BigDecimal.valueOf(distance));
    }

    private Pair<BigDecimal, BigDecimal> calculateFinalPrice(
            BigDecimal productsPrice,
            BigDecimal transportationPrice,
            Discount discount
    ) {
        var discountValue = BigDecimal.valueOf(1 - discount.getValue() / 100D);
        var subtotal = productsPrice.add(transportationPrice);
        var total = productsPrice.multiply(discountValue).setScale(2, RoundingMode.HALF_UP);
        return Pair.of(subtotal, total);
    }

    @NonNull
    private BigDecimal calculateProductPrice(List<ProductPriceCalculation> prices, Entry<Product, Integer> products) {
        var product = products.getKey();
        var quantity = products.getValue();
        checkProductAvailability(product, quantity);
        var price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        prices.add(new ProductPriceCalculation(product, quantity, price));
        return price;
    }

    private void checkProductAvailability(Product product, Integer quantity) {
        var quantityLeft = product.getQuantity();
        var isAvailable = (quantity <= quantityLeft) || quantityLeft == -1;
        if (!isAvailable) {
            throw new ProductNotAvailableException(product.getId(), quantity);
        }
    }
}
