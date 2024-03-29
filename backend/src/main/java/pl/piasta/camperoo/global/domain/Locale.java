package pl.piasta.camperoo.global.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.piasta.camperoo.common.domain.AbstractEntity;

import static java.util.Objects.requireNonNullElse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "locales")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Locale extends AbstractEntity {
    public static final Long PL = 1L;
    public static final Long US = 2L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 3)
    private String languageCode;

    @Column(name = "alpha2_code", nullable = false, length = 2)
    private String alpha2Code;

    @Column(name = "alpha3_code", nullable = false, length = 3)
    private String alpha3Code;

    @Column(nullable = false, length = 3)
    private String numericCode;

    @Column(nullable = false, length = 5)
    private String callingCode;

    @Column(nullable = false, insertable = false, updatable = false)
    private Boolean fallback;

    @PostLoad
    void initFallback() {
        fallback = requireNonNullElse(fallback, false);
    }
}
