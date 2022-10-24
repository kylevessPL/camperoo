package pl.piasta.camperoo.localization.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
