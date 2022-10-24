package pl.piasta.camperoo.place.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "company_branches")
public class CompanyBranch extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_company_branches_id", sequenceName = "seq_company_branches_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_company_branches_id")
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, precision = 7)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 7)
    private BigDecimal longtitude;

    @Column
    private String notes;
}
