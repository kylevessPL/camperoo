package pl.piasta.camperoo.delivery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "delivery_types")
public class DeliveryType extends AbstractEntity
        implements LocalizableName<DeliveryTypeName>, LocalizableDescription<DeliveryTypeDescription> {
    public static final Long DOOR_TO_DOOR = 1L;
    public static final Long SELF_PICKUP = 2L;

    @Id
    private Long id;

    @Column(name = "code", nullable = false, length = 60)
    private String code;

    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.EAGER)
    private Set<DeliveryTypeName> names;

    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.EAGER)
    private Set<DeliveryTypeDescription> descriptions;
}
