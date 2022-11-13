package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.user.domain.User;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id) {
        super(User.class, "id", id, ErrorCode.USER_NOT_FOUND, ErrorProperty.USER_NOT_FOUND);
    }
}


