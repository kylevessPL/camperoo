package pl.piasta.camperoo.user.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaVerificationResult {
    private boolean success;

    @Getter(AccessLevel.NONE)
    @JsonAlias("error-codes")
    private List<CaptchaError> errors = new ArrayList<>();

    public CaptchaError getError() {
        return CollectionUtils.firstElement(errors);
    }
}
