package pl.piasta.camperoo.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.dto.ResourceCreatedDto;
import pl.piasta.camperoo.user.domain.UserFacade;
import pl.piasta.camperoo.user.dto.CreateAccountDto;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserFacade userFacade;

    @ResponseStatus(CREATED)
    @PostMapping
    public ResourceCreatedDto createCustomerAccount(@RequestBody @Valid CreateAccountDto dto) {
        return userFacade.createAccount(dto);
    }
}
