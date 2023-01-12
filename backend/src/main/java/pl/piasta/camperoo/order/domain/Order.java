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
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
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
import lombok.Singular;
import pl.piasta.camperoo.branch.domain.CompanyBranch;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.delivery.domain.DeliveryType;
import pl.piasta.camperoo.discount.domain.Discount;
import pl.piasta.camperoo.file.domain.File;
import pl.piasta.camperoo.payment.domain.Payment;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.exception.MissingPaymentException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "orders")
@NamedEntityGraph(
        name = "orders-basic-graph",
        attributeNodes = {
                @NamedAttributeNode("invoice"),
                @NamedAttributeNode(value = "deliveryType", subgraph = "name-description-graph"),
                @NamedAttributeNode(value = "status", subgraph = "name-description-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "name-description-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
                        }
                ),
                @NamedSubgraph(
                        name = "name-description-child-graph",
                        attributeNodes = {
                                @NamedAttributeNode("locale")
                        }
                )
        }
)
@NamedEntityGraph(
        name = "orders-graph",
        attributeNodes = {
                @NamedAttributeNode("discount"),
                @NamedAttributeNode("invoice"),
                @NamedAttributeNode("companyBranch"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "payments", subgraph = "payments-graph"),
                @NamedAttributeNode(value = "products", subgraph = "order-products-graph"),
                @NamedAttributeNode(value = "deliveryType", subgraph = "name-description-graph"),
                @NamedAttributeNode(value = "status", subgraph = "name-description-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "payments-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "type", subgraph = "payment-types-graph"),
                                @NamedAttributeNode(value = "status", subgraph = "name-description-graph"),
                        }
                ),
                @NamedSubgraph(
                        name = "payment-types-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph")
                        }
                ),
                @NamedSubgraph(
                        name = "order-products-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "product", subgraph = "products-graph")
                        }
                ),
                @NamedSubgraph(
                        name = "products-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
                                @NamedAttributeNode(value = "category", subgraph = "name-description-graph"),
                                @NamedAttributeNode(value = "image"),
                        }
                ),
                @NamedSubgraph(
                        name = "name-description-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
                        }
                ),
                @NamedSubgraph(
                        name = "name-description-child-graph",
                        attributeNodes = {
                                @NamedAttributeNode("locale")
                        }
                )
        }
)
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
    private Integer days;

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

    @Singular
    @OrderBy("deadline DESC")
    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<>();

    @Singular
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> products;

    public Optional<Payment> getPayment() {
        return payments.stream().findFirst();
    }

    public void updateStatus(OrderStatus status) {
        if (status.isProcessed()) {
            checkIfPaymentReceived();
        }
        this.status = status;
        this.statusChangeDate = Instant.now();
    }

    public void addInvoice(File invoice) {
        this.invoice = invoice;
    }

    private void checkIfPaymentReceived() {
        if (isNull(getPayment())) {
            throw new MissingPaymentException(id);
        }
    }
}
