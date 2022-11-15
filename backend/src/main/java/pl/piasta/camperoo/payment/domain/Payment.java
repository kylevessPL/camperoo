package pl.piasta.camperoo.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.order.domain.Order;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_id", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "INT8")
@Table(name = "payments")
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private PaymentStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    protected Payment(Long id, Instant deadline, Instant statusChangeDate, PaymentStatus status, Order order) {
        this.id = id;
        this.deadline = deadline;
        this.statusChangeDate = statusChangeDate;
        this.status = status;
        this.order = order;
    }
}
