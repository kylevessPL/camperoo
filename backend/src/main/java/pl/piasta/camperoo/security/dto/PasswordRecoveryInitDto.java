package pl.piasta.camperoo.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.EmailCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryInitDto {
    @NotBlank
    @EmailCheck
    private String email;
}

