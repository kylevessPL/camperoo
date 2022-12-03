package pl.piasta.camperoo.branch.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
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
    private BigDecimal longitude;

    @Embedded
    @Column(nullable = false)
    private EmailAddress email;

    @Column(length = 9)
    private String phoneNumber;
}
