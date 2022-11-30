package pl.piasta.camperoo.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class VerificationTokenCode implements ValueObject {
    private UUID code;

    private VerificationTokenCode(UUID code) {
        this.code = code;
    }

    public static VerificationTokenCode of(UUID code) {
        return new VerificationTokenCode(code);
    }

    public static VerificationTokenCode random() {
        return new VerificationTokenCode(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return code.toString();
    }
}
