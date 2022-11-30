package pl.piasta.camperoo.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.EmailCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryInitDto {
    @NotBlank(message = "{validation.email.blank}")
    @EmailCheck
    private String email;
}

