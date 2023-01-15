package pl.piasta.camperoo.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.vo.Password;
import pl.piasta.camperoo.security.dto.PasswordRecoveryDto;
import pl.piasta.camperoo.security.dto.PasswordRecoveryInitDto;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final AuthenticationEmailNotifier authenticationEmailNotifier;
    private final AuthenticationPasswordManager authenticationPasswordManager;
    private final AuthenticationRepository authenticationRepository;

    @PreAuthorize("permitAll()")
    public void recoverPassword(PasswordRecoveryDto dto) {
        var token = VerificationTokenCode.of(UUID.fromString(dto.getCode()));
        Password password = Password.of(dto.getPassword());
        authenticationPasswordManager.recover(token, password);
    }

    @PreAuthorize("permitAll()")
    public void generatePasswordRecoveryToken(PasswordRecoveryInitDto dto) {
        var emailAddress = EmailAddress.of(dto.getEmail());
        authenticationRepository.findByEmailAddress(emailAddress).ifPresent(user -> {
            var recoveryToken = authenticationPasswordManager.generateRecoveryToken(user);
            authenticationEmailNotifier.sendPasswordResetToken(recoveryToken, user);
        });
    }
}
