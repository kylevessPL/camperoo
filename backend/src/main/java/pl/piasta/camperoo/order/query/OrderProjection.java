package pl.piasta.camperoo.order.query;

import org.springframework.beans.factory.annotation.Value;
import pl.piasta.camperoo.branch.query.CompanyBranchProjection;
import pl.piasta.camperoo.discount.query.DiscountProjection;
import pl.piasta.camperoo.payment.query.PaymentProjection;
import pl.piasta.camperoo.product.query.ProductBasicProjection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface OrderProjection extends OrderBasicProjection {
    BigDecimal getSubtotalPrice();

    String getAddress();

    BigDecimal getLatitude();

    BigDecimal getLongitude();

    String getNotes();

    DiscountProjection getDiscount();

    CompanyBranchProjection getCompanyBranch();

    @Value("#{target.user?.id}")
    Long getUserId();

    List<PaymentProjection> getPayments();

    Set<OrderProductProjection> getProducts();

    interface OrderProductProjection {
        ProductBasicProjection getProduct();

        Integer getQuantity();

        BigDecimal getTotalPrice();
    }
}
