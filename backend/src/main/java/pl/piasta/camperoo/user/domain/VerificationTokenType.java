package pl.piasta.camperoo.user.domain;

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
@Table(name = "verification_token_types")
public class VerificationTokenType extends AbstractEntity {

    public static final Long ACCOUNT_CREATION = 1L;
    public static final Long ACCOUNT_RECOVERY = 2L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;
}
