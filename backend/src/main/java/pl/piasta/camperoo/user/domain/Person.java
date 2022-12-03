package pl.piasta.camperoo.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.user.domain.vo.PhoneNumber;
import pl.piasta.camperoo.user.domain.vo.ZipCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "persons")
public class Person extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_persons_id", sequenceName = "seq_persons_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_persons_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String addressOne;

    @Column
    private String addressTwo;

    @Embedded
    @Column(nullable = false, length = 6)
    private ZipCode zipCode;

    @Column(nullable = false, length = 60)
    private String city;

    @Embedded
    @Column(nullable = false, length = 9)
    private PhoneNumber phoneNumber;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = false)
    private User user;
}
