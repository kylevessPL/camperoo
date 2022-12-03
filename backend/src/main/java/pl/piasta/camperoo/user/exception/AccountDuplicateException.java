package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.exception.DuplicateException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class AccountDuplicateException extends DuplicateException {
    public AccountDuplicateException(EmailAddress emailAddress) {
        super(
                String.format("Account with given email address = '%s' already exists", emailAddress.toString()),
                ErrorCode.ACCOUNT_ALREADY_EXISTS, ErrorProperty.ACCOUNT_ALREADY_EXISTS,
                "email", emailAddress.toString()
        );
    }
}
