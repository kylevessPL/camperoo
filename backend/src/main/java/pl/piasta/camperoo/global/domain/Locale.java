package pl.piasta.camperoo.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "locales")
public class Locale extends AbstractEntity {
    public static final Long PL = 1L;
    public static final Long US = 2L;

    @Id
    private Long id;

    @Column(nullable = false, length = 5)
    private String code;

    @Column(nullable = false)
    private boolean fallback;
}
