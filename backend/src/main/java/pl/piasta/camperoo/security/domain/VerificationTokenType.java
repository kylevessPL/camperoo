package pl.piasta.camperoo.security.domain;

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
@Table(name = "verification_token_types")
public class VerificationTokenType extends AbstractEntity {

    public static final Long ACCOUNT_CREATION = 1L;
    public static final Long PASSWORD_RECOVERY = 2L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;
}
