package pl.piasta.camperoo.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@Jacksonized
public class LoginResultDto {
    @NotNull
    Long issuanceTime;
    @NotNull
    Long expirationTime;
    @NotEmpty
    Set<String> roles;
}
