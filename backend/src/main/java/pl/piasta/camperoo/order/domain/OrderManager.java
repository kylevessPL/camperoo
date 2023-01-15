package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import pl.piasta.camperoo.discount.domain.Discount;
import pl.piasta.camperoo.order.exception.DeliveryTypeNotFoundException;
import pl.piasta.camperoo.order.exception.DiscountNotFoundException;
import pl.piasta.camperoo.order.exception.OrderNotFoundException;
import pl.piasta.camperoo.order.exception.OrderStatusNotFoundException;
import pl.piasta.camperoo.product.domain.Product;
import pl.piasta.camperoo.product.exception.ProductNotFoundException;
import pl.piasta.camperoo.user.exception.UserNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElseGet;

@RequiredArgsConstructor
class OrderManager {
    private final OrderInvoiceManager invoiceManager;
    private final OrderEmailNotifier orderEmailNotifier;
    private final OrderCalculationManager calculationManager;
    private final OrderCompanyBranchManager companyBranchManager;
    private final OrderUserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderDiscountRepository orderDiscountRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderDeliveryTypeRepository orderDeliveryTypeRepository;

    public Order changeOrderStatus(Long orderId, Long statusId) {
        var order = orderRepository.get(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        var status = orderStatusRepository.get(statusId).orElseThrow(() -> new OrderStatusNotFoundException(statusId));
        order.updateStatus(status);
        if (isNull(order.getInvoice()) && status.isProcessed()) {
            var invoice = invoiceManager.generateInvoice(order);
            order.addInvoice(invoice);
            orderEmailNotifier.sendOrderInvoiceGeneratedEmail(order);
        }
        return order;
    }

    public OrderCalculationResult calculateOrder(OrderCalculationDetails details) {
        var days = details.days();
        var discount = getDiscount(details.discountCode());
        var products = getProductsWithQuantity(details.products());
        var branch = companyBranchManager.findNearestCompanyBranch(details.deliveryTypeId(), details.destination());
        return calculationManager.calculateOrderPrice(days, products, branch, discount);
    }

    public Order createOrder(OrderPlacementDetails placementDetails, OrderCalculationResult calculationResult) {
        var userId = placementDetails.userId();
        var typeId = placementDetails.deliveryTypeId();
        var destination = placementDetails.destination();
        var user = userRepository.get(userId).orElseThrow(() -> new UserNotFoundException(userId));
        var type = orderDeliveryTypeRepository.get(typeId).orElseThrow(() -> new DeliveryTypeNotFoundException(typeId));
        var orderStatus = orderStatusRepository.getReference(OrderStatus.PLACED);
        var order = Order.builder()
                .days(calculationResult.days())
                .latitude(destination.getLatitude())
                .longitude(destination.getLongitude())
                .address(placementDetails.address())
                .notes(placementDetails.notes())
                .companyBranch(calculationResult.companyBranch())
                .deliveryType(type)
                .discount(calculationResult.discount())
                .subtotalPrice(calculationResult.priceSubtotal())
                .totalPrice(calculationResult.priceTotal())
                .placementDate(Instant.now())
                .statusChangeDate(Instant.now())
                .status(orderStatus)
                .user(user)
                .build();
        addOrderProducts(order, calculationResult.productCalculations());
        return orderRepository.save(order);
    }

    @NonNull
    private Map<Product, Integer> getProductsWithQuantity(Map<Long, Integer> productQuantities) {
        var products = orderProductRepository.getAllByIdIn(productQuantities.keySet())
                .stream()
                .map(e -> Map.entry(e.getId(), e))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return productQuantities.entrySet()
                .stream()
                .map(entry -> zipProductQuantity(products, entry))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    @NonNull
    private Entry<Product, Integer> zipProductQuantity(Map<Long, Product> products, Entry<Long, Integer> entry) {
        var product = requireNonNullElseGet(
                products.get(entry.getKey()),
                () -> {throw new ProductNotFoundException(entry.getKey());}
        );
        return Map.entry(product, entry.getValue());
    }

    private Discount getDiscount(String discountCode) {
        if (isNull(discountCode)) {
            return null;
        }
        return orderDiscountRepository
                .getByCode(discountCode)
                .filter(Discount::isActive)
                .orElseThrow(() -> new DiscountNotFoundException(discountCode));
    }

    private void addOrderProducts(Order order, List<OrderProductCalculation> productCalculations) {
        productCalculations
                .stream()
                .map(calculation -> createOrderProduct(order, calculation))
                .forEach(product -> order.getProducts().add(product));
    }

    @NonNull
    private OrderProduct createOrderProduct(Order order, OrderProductCalculation productCalculation) {
        var product = productCalculation.product();
        var quantity = productCalculation.quantity();
        var totalPrice = productCalculation.totalPrice();
        return new OrderProduct(order, product, quantity, totalPrice);
    }
}
