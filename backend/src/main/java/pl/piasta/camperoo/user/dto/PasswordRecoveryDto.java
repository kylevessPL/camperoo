package pl.piasta.camperoo.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import pl.piasta.camperoo.common.validation.PasswordCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryDto {
    @NotBlank
    @UUID
    private String code;

    @NotBlank
    @PasswordCheck
    private String password;
}

