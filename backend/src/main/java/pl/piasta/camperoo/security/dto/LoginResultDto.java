package pl.piasta.camperoo.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@Jacksonized
public class LoginResultDto {
    @Min(0)
    Long expirationTime;
    @NotEmpty
    Set<String> roles;
}
