package pl.piasta.camperoo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResultDto {
    @NotNull(message = "{validation.issuanceTime.null}")
    private Long issuanceTime;

    @NotNull(message = "{validation.expirationTime.null}")
    private Long expirationTime;

    @NotEmpty(message = "{validation.roles.empty}")
    private Set<String> roles;
}
