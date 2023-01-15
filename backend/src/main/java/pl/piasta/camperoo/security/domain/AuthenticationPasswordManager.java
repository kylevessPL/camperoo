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
    private final AuthenticationTokenCleanupScheduler tokenCleanupScheduler;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final AuthenticationTokenTypeRepository authenticationTokenTypeRepository;
    private final long passwordRecoveryTokenValidMinutes;

    public void recover(VerificationTokenCode token, Password password) {
        var passwordRecoveryTokenType = authenticationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        VerificationToken passwordRecoveryToken =
                authenticationTokenRepository.findByIdAndType(token, passwordRecoveryTokenType)
                        .filter(VerificationToken::isValid)
                        .orElseThrow(() -> VerificationTokenNotFoundException.passwordRecovery(token));
        reset(passwordRecoveryToken.getUser(), password);
        authenticationTokenRepository.delete(passwordRecoveryToken.getId());
    }

    public VerificationTokenCode generateRecoveryToken(User user) {
        var expirationDate = Instant.now().plus(passwordRecoveryTokenValidMinutes, MINUTES);
        var passwordRecoveryTokenType = authenticationTokenTypeRepository.getReference(PASSWORD_RECOVERY);
        var passwordRecoveryToken = VerificationToken.builder()
                .code(VerificationTokenCode.random())
                .expirationDate(expirationDate)
                .type(passwordRecoveryTokenType)
                .user(user)
                .build();
        var token = authenticationTokenRepository.save(passwordRecoveryToken);
        tokenCleanupScheduler.scheduleExpiredVerificationTokenCleanup(token.getId(), expirationDate);
        return token.getCode();
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
