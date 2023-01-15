package pl.piasta.camperoo.verificationtoken.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.user.domain.User;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_verification_tokens_id",
                       sequenceName = "seq_verification_tokens_id",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_verification_tokens_id")
    private Long id;

    @Embedded
    private VerificationTokenCode code;

    @Column(nullable = false)
    private Instant expirationDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private VerificationTokenType type;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public boolean isValid() {
        return !expirationDate.isBefore(Instant.now());
    }
}
