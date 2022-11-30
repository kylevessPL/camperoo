package pl.piasta.camperoo.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.security.domain.AuthenticationFacade;
import pl.piasta.camperoo.user.dto.PasswordRecoveryDto;
import pl.piasta.camperoo.user.dto.PasswordRecoveryInitDto;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final AuthenticationFacade authenticationFacade;

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/password-recovery")
    void recoverPassword(@RequestBody @Valid PasswordRecoveryDto dto) {
        authenticationFacade.recoverPassword(dto);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/password-recovery/init")
    void initPasswordRecovery(@RequestBody @Valid PasswordRecoveryInitDto dto) {
        authenticationFacade.generatePasswordRecoveryToken(dto);
    }
}
