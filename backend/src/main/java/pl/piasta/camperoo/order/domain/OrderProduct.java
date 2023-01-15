package pl.piasta.camperoo.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.product.domain.Product;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders_products")
public class OrderProduct {
    @EmbeddedId
    private OrderProductPK id;

    @Version
    private Long version;

    @MapsId("orderId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 2)
    private BigDecimal totalPrice;

    public OrderProduct(Order order, Product product, Integer quantity, BigDecimal totalPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.id = new OrderProductPK(order.getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof OrderProduct orderProduct && Objects.equals(id, orderProduct.getId());
    }
}
