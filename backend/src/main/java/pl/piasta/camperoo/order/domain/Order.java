package pl.piasta.camperoo.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.delivery.domain.DeliveryType;
import pl.piasta.camperoo.discount.domain.Discount;
import pl.piasta.camperoo.file.domain.File;
import pl.piasta.camperoo.payment.domain.Payment;
import pl.piasta.camperoo.user.domain.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_orders_id", sequenceName = "seq_orders_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_orders_id")
    private Long id;

    @Column(nullable = false)
    private Instant placementDate;

    @Column
    private Instant statusChangeDate;

    @Column(nullable = false, precision = 2)
    private BigDecimal subtotalPrice;

    @Column(nullable = false, precision = 2)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, precision = 7)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 7)
    private BigDecimal longitude;

    @Column
    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", unique = true)
    private File invoice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_branch_id", nullable = false)
    private CompanyBranch companyBranch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryType deliveryType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OrderBy("deadline DESC")
    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Optional<Payment> getPayment() {
        return payments.stream().findFirst();
    }
}
