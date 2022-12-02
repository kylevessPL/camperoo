package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class AccountWithoutRoleException extends BusinessException {
    public AccountWithoutRoleException() {
        super("Account must have at least one role", ErrorProperty.ACCOUNT_WITHOUT_ROLE);
    }
}
