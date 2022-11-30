package pl.piasta.camperoo.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.vo.Password;
import pl.piasta.camperoo.security.exception.PasswordUnchangedException;
import pl.piasta.camperoo.security.exception.VerificationTokenNotFoundException;
import pl.piasta.camperoo.user.domain.User;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;
import static pl.piasta.camperoo.security.domain.VerificationTokenType.PASSWORD_RECOVERY;

@RequiredArgsConstructor
class AuthenticationPasswordManager {
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenTypeRepository verificationTokenTypeRepository;
    private final int recoveryTokenValidMinutes;

    public void recover(VerificationTokenCode token, Password password) {
        var recoveryTokenType = verificationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        VerificationToken recoveryToken = verificationTokenRepository.findByIdAndType(token, recoveryTokenType)
                .filter(verificationToken -> !verificationToken.isExpired())
                .orElseThrow(() -> VerificationTokenNotFoundException.passwordRecovery(token));
        reset(recoveryToken.getUser(), password);
        verificationTokenRepository.delete(recoveryToken.getId());
    }

    public VerificationTokenCode generateRecoveryToken(User user) {
        var expirationDate = Instant.now().plus(recoveryTokenValidMinutes, MINUTES);
        var recoveryTokenType = verificationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        var recoveryToken = VerificationToken.builder()
                .code(VerificationTokenCode.random())
                .expirationDate(expirationDate)
                .type(recoveryTokenType)
                .user(user)
                .build();
        return verificationTokenRepository.save(recoveryToken).getCode();
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
