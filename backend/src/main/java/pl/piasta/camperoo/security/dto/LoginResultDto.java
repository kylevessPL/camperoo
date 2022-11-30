package pl.piasta.camperoo.security.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResultDto {
    @NotNull(message = "{validation.issuanceTime.null}")
    private Long issuanceTime;

    @NotNull(message = "{validation.expirationTime.null}")
    private Long expirationTime;

    @NotEmpty(message = "{validation.role.empty}")
    private Set<String> roles;
}
