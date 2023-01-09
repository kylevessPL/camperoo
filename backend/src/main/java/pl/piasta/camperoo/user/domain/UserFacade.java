package pl.piasta.camperoo.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.common.dto.ResourceCreatedDto;
import pl.piasta.camperoo.user.dto.ConfirmAccountDto;
import pl.piasta.camperoo.user.dto.CreateAccountDto;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class UserFacade {
    private final UserConverter userConverter;
    private final UserAccountManager userAccountManager;
    private final UserEmailNotifier userEmailNotifier;

    @PreAuthorize("permitAll()")
    public ResourceCreatedDto createAccount(CreateAccountDto dto) {
        var user = userConverter.convert(dto);
        var accountCreationToken = userAccountManager.createCustomer(user);
        user = accountCreationToken.getUser();
        userEmailNotifier.sendAccountCreationToken(accountCreationToken.getCode(), user);
        return new ResourceCreatedDto(user.getId());
    }

    @PreAuthorize("permitAll()")
    public void confirmAccount(ConfirmAccountDto dto) {
        var token = VerificationTokenCode.of(UUID.fromString(dto.getCode()));
        userAccountManager.confirmAccount(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void updateAccountStatus(Long userId, boolean active) {
        userAccountManager.updateAccountStatus(userId, active);
    }
}
