package pl.piasta.camperoo.user.domain;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.user.exception.AccountDuplicateException;
import pl.piasta.camperoo.verificationtoken.domain.VerificationToken;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;
import static pl.piasta.camperoo.user.domain.Role.CUSTOMER;
import static pl.piasta.camperoo.verificationtoken.domain.VerificationTokenType.ACCOUNT_CREATION;

@RequiredArgsConstructor
class UserAccountManager {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAccountTokenRepository userAccountTokenRepository;
    private final UserAccountTokenTypeRepository userAccountTokenTypeRepository;
    private final int accountCreationTokenValidMinutes;

    public VerificationToken createCustomer(User user) {
        var role = roleRepository.getReference(CUSTOMER);
        user = createUser(user, role);
        return generateAccountCreationToken(user);
    }

    private User createUser(User user, Role role) {
        var emailAddress = user.getEmailAddress();
        if (userRepository.existsByEmailAddress(emailAddress)) {
            throw new AccountDuplicateException(emailAddress);
        }
        user.changeRoles(role);
        return userRepository.save(user);
    }

    private VerificationToken generateAccountCreationToken(User user) {
        var expirationDate = Instant.now().plus(accountCreationTokenValidMinutes, MINUTES);
        var accountCreationTokenType = userAccountTokenTypeRepository.getReference(ACCOUNT_CREATION);
        var accountCreationToken = VerificationToken.builder()
                .code(VerificationTokenCode.random())
                .expirationDate(expirationDate)
                .type(accountCreationTokenType)
                .user(user)
                .build();
        return userAccountTokenRepository.save(accountCreationToken);
    }
}
