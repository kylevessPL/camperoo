package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.MutablePair;
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

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
class OrderCalculationManager {
    private final OrderProductRepository productRepository;

    public OrderCalculationResult calculateOrderPrice(
            int days,
            Map<Product, Integer> products,
            Pair<CompanyBranch, Integer> companyBranch,
            Discount discount
    ) {
        var productsPrice = calculateProductsPrice(products, days);
        var transportationPrice = calculateTransportationTotalPrice(productsPrice, companyBranch.getValue());
        var discountValue = getDiscountValue(discount);
        var finalPrice = calculateFinalPrice(productsPrice.getValue(), discountValue);
        return new OrderCalculationResult(
                days,
                discount,
                companyBranch.getLeft(),
                companyBranch.getRight(),
                productsPrice.getLeft(),
                transportationPrice,
                finalPrice.getLeft(),
                finalPrice.getRight()
        );
    }

    private Pair<List<OrderProductCalculation>, BigDecimal> calculateProductsPrice(
            Map<Product, Integer> products,
            int days
    ) {
        List<OrderProductCalculation> prices = new ArrayList<>();
        var totalPrice = products.entrySet()
                .stream()
                .map(e -> calculateProductPrice(prices, e, days))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return MutablePair.of(prices, totalPrice);
    }

    private BigDecimal calculateTransportationTotalPrice(
            Pair<List<OrderProductCalculation>, BigDecimal> products,
            Integer distance
    ) {
        var transportation = productRepository.getTransportationProduct();
        var price = transportation.getPrice().multiply(BigDecimal.valueOf(distance));
        var calculation = new OrderProductCalculation(transportation, distance, price);
        products.getKey().add(calculation);
        products.setValue(products.getValue().add(price));
        return price;
    }

    private Pair<BigDecimal, BigDecimal> calculateFinalPrice(BigDecimal subtotal, double discountValue) {
        var discountRate = BigDecimal.valueOf(1 - discountValue);
        var total = subtotal.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
        return Pair.of(subtotal, total);
    }

    @NonNull
    private BigDecimal calculateProductPrice(
            List<OrderProductCalculation> prices,
            Entry<Product, Integer> products,
            int days
    ) {
        var product = products.getKey();
        var quantity = products.getValue();
        checkProductAvailability(product, quantity);
        var price = product.getPrice()
                .multiply(BigDecimal.valueOf(quantity))
                .multiply(BigDecimal.valueOf(days));
        prices.add(new OrderProductCalculation(product, quantity, price));
        return price;
    }

    private double getDiscountValue(Discount discount) {
        if (nonNull(discount)) {
            return discount.getValue() / 100D;
        }
        return 0;
    }

    private void checkProductAvailability(Product product, Integer quantity) {
        var quantityLeft = product.getQuantity();
        var isAvailable = (quantity <= quantityLeft) || quantityLeft == -1;
        if (!isAvailable) {
            throw new ProductNotAvailableException(product.getId(), quantity);
        }
    }
}
