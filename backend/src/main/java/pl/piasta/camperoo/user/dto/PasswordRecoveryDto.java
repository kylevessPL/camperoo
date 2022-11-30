package pl.piasta.camperoo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.PasswordCheck;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryDto {
    @NotNull(message = "{validation.code.null}")
    private UUID code;

    @NotBlank(message = "{validation.password.blank}")
    @PasswordCheck
    private String password;
}

