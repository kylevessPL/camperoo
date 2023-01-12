package pl.piasta.camperoo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.validation.AlphaNumericCheck;
import pl.piasta.camperoo.common.validation.EmailCheck;
import pl.piasta.camperoo.common.validation.PasswordCheck;
import pl.piasta.camperoo.common.validation.PhoneNumberCheck;
import pl.piasta.camperoo.common.validation.ZipCodeCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    @NotBlank
    private String captchaToken;

    @NotBlank
    @EmailCheck
    private String email;

    @NotBlank
    @PasswordCheck
    private String password;

    @NotBlank
    @Size(max = 255)
    @AlphaNumericCheck
    private String firstName;

    @NotBlank
    @Size(max = 255)
    @AlphaNumericCheck
    private String lastName;

    @NotBlank
    @Size(max = 255)
    @AlphaNumericCheck
    private String addressOne;

    @Size(min = 1, max = 255)
    @AlphaNumericCheck
    private String addressTwo;

    @NotBlank
    @ZipCodeCheck
    private String zipCode;

    @NotBlank
    @Size(max = 60)
    @AlphaNumericCheck
    private String city;

    @NotBlank
    @PhoneNumberCheck
    private String phoneNumber;
}
