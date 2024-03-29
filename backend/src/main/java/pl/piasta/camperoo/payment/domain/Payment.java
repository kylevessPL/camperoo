package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.order.domain.Order;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_id", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "INT8")
@Table(name = "payments")
@NamedEntityGraph(
        name = "payments-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "type", subgraph = "payment-types-graph"),
                @NamedAttributeNode(value = "status", subgraph = "name-description-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "payment-types-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph")
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
public class Payment extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_payments_id", sequenceName = "seq_payments_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_payments_id")
    private Long id;

    @Column(nullable = false)
    private Instant deadline;

    @Column
    private Instant statusChangeDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", nullable = false, insertable = false, updatable = false)
    private PaymentType type;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private PaymentStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    protected Payment(Long id, Instant deadline, Instant statusChangeDate, PaymentStatus status, Order order) {
        this.id = id;
        this.deadline = deadline;
        this.statusChangeDate = statusChangeDate;
        this.status = status;
        this.order = order;
    }

    public void updateStatus(PaymentStatus status) {
        this.status = status;
        this.statusChangeDate = Instant.now();
    }

    public boolean isValid() {
        return !deadline.isBefore(Instant.now());
    }
}
