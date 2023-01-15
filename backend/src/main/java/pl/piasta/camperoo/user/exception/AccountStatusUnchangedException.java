package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class AccountStatusUnchangedException extends BusinessException {

    public AccountStatusUnchangedException(Long id, String messsage, ErrorProperty errorProperty) {
        super(messsage, ErrorCode.ACCOUNT_STATUS_UNCHANGED, errorProperty, id);
    }

    public static AccountStatusUnchangedException enabled(Long id) {
        return new AccountStatusUnchangedException(
                id,
                "Account of user with id %d is already enabled".formatted(id),
                ErrorProperty.ACCOUNT_ALREADY_ENABLED
        );
    }

    public static AccountStatusUnchangedException disabled(Long id) {
        return new AccountStatusUnchangedException(
                id,
                "Account of user with id %d is already disabled".formatted(id),
                ErrorProperty.ACCOUNT_ALREADY_DISABLED
        );
    }
}
