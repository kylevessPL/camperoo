package pl.piasta.camperoo.security.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.UnixTimestampCheck;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResultDto {
    @NotNull
    private Long userId;

    @NotNull
    @UnixTimestampCheck
    private Long issuanceTime;

    @NotNull
    @UnixTimestampCheck
    private Long expirationTime;

    @NotEmpty
    private Set<String> roles;
}
