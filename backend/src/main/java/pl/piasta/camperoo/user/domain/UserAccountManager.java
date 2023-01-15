package pl.piasta.camperoo.user.domain;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.user.exception.AccountDuplicateException;
import pl.piasta.camperoo.user.exception.UserNotFoundException;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;
import pl.piasta.camperoo.verificationtoken.exception.VerificationTokenNotFoundException;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;
import static pl.piasta.camperoo.user.domain.Role.CUSTOMER;
import static pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType.ACCOUNT_CREATION;

@RequiredArgsConstructor
class UserAccountManager {
    private final UserTokenCleanupScheduler userTokenCleanupScheduler;
    private final UserUserRepository userRepository;
    private final UserPersonRepository personRepository;
    private final UserRoleRepository roleRepository;
    private final UserTokenRepository userTokenRepository;
    private final UserTokenTypeRepository userTokenTypeRepository;
    private final long accountCreationTokenValidMinutes;

    public VerificationToken createCustomer(User user) {
        var role = roleRepository.getReference(CUSTOMER);
        user = createUser(user, role);
        return generateAccountCreationToken(user);
    }

    public void confirmAccount(VerificationTokenCode token) {
        var accountCreationTokenType = userTokenTypeRepository.getReference(ACCOUNT_CREATION);
        VerificationToken accountCreationToken = userTokenRepository.findByIdAndType(token, accountCreationTokenType)
                .filter(VerificationToken::isValid)
                .orElseThrow(() -> VerificationTokenNotFoundException.accountCreation(token));
        accountCreationToken.getUser().enableAccount();
        userTokenRepository.delete(accountCreationToken.getId());
    }

    public void updateAccountStatus(Long userId, boolean active) {
        userRepository.get(userId).ifPresentOrElse(
                user -> changeStatus(user, active),
                () -> {throw new UserNotFoundException(userId);}
        );
    }

    private User createUser(User user, Role role) {
        var emailAddress = user.getEmail();
        if (userRepository.existsByEmailAddress(emailAddress)) {
            throw new AccountDuplicateException(emailAddress);
        }
        user = user.toBuilder()
                .role(role)
                .build();
        personRepository.save(user.getPerson());
        return userRepository.save(user);
    }

    private VerificationToken generateAccountCreationToken(User user) {
        var expirationDate = Instant.now().plus(accountCreationTokenValidMinutes, MINUTES);
        var accountCreationTokenType = userTokenTypeRepository.getReference(ACCOUNT_CREATION);
        var accountCreationToken = VerificationToken.builder()
                .code(VerificationTokenCode.random())
                .expirationDate(expirationDate)
                .type(accountCreationTokenType)
                .user(user)
                .build();
        var token = userTokenRepository.save(accountCreationToken);
        userTokenCleanupScheduler.scheduleExpiredVerificationTokenCleanup(token.getId(), expirationDate);
        return token;
    }

    private void changeStatus(User user, boolean active) {
        if (active) {
            user.enableAccount();
        } else {
            user.disableAccount();
        }
    }
}
