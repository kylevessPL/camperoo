package pl.piasta.camperoo.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.vo.Password;
import pl.piasta.camperoo.security.exception.PasswordUnchangedException;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;
import pl.piasta.camperoo.verificationtoken.exception.VerificationTokenNotFoundException;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;
import static pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType.PASSWORD_RECOVERY;

@RequiredArgsConstructor
class AuthenticationPasswordManager {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final AuthenticationTokenTypeRepository authenticationTokenTypeRepository;
    private final int passwordRecoveryTokenValidMinutes;

    public void recover(VerificationTokenCode token, Password password) {
        var recoveryTokenType = authenticationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        VerificationToken recoveryToken = authenticationTokenRepository.findByIdAndType(token, recoveryTokenType)
                .filter(verificationToken -> !verificationToken.isExpired())
                .orElseThrow(() -> VerificationTokenNotFoundException.passwordRecovery(token));
        reset(recoveryToken.getUser(), password);
        authenticationTokenRepository.delete(recoveryToken.getId());
    }

    public VerificationTokenCode generateRecoveryToken(User user) {
        var expirationDate = Instant.now().plus(passwordRecoveryTokenValidMinutes, MINUTES);
        var recoveryTokenType = authenticationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        var recoveryToken = VerificationToken.builder()
                .code(VerificationTokenCode.random())
                .expirationDate(expirationDate)
                .type(recoveryTokenType)
                .user(user)
                .build();
        return authenticationTokenRepository.save(recoveryToken).getCode();
    }

    private void reset(User user, Password password) {
        var oldPasswordHash = user.getPasswordHash();
        var newPassword = password.getValue();
        if (passwordEncoder.matches(newPassword, oldPasswordHash)) {
            throw new PasswordUnchangedException();
        }
        var newPasswordHash = passwordEncoder.encode(newPassword);
        user.changePasswordHash(newPasswordHash);
    }
}
