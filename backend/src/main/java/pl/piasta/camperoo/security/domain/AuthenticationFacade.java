package pl.piasta.camperoo.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.vo.Password;
import pl.piasta.camperoo.user.dto.PasswordRecoveryDto;
import pl.piasta.camperoo.user.dto.PasswordRecoveryInitDto;

@RequiredArgsConstructor
public class AuthenticationFacade {
    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationPasswordManager authenticationPasswordManager;
    private final AuthenticationEmailNotifier authenticationEmailNotifier;

    @Transactional
    @PreAuthorize("permitAll()")
    public void recoverPassword(PasswordRecoveryDto dto) {
        var token = VerificationTokenCode.of(dto.getCode());
        Password password = Password.of(dto.getPassword());
        authenticationPasswordManager.recover(token, password);
    }

    @Transactional
    @PreAuthorize("permitAll()")
    public void generatePasswordRecoveryToken(PasswordRecoveryInitDto dto) {
        var emailAddress = EmailAddress.of(dto.getEmail());
        authenticationRepository.findByEmail(emailAddress).ifPresent(user -> {
            var recoveryToken = authenticationPasswordManager.generateRecoveryToken(user);
            authenticationEmailNotifier.sendPasswordResetToken(recoveryToken, user);
        });
    }
}
