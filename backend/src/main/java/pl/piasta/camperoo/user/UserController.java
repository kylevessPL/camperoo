package pl.piasta.camperoo.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.dto.ResourceCreatedDto;
import pl.piasta.camperoo.common.util.HttpRequestUtils;
import pl.piasta.camperoo.security.TokenPrincipal;
import pl.piasta.camperoo.user.domain.UserFacade;
import pl.piasta.camperoo.user.dto.ConfirmAccountDto;
import pl.piasta.camperoo.user.dto.CreateAccountDto;
import pl.piasta.camperoo.user.query.UserBasicProjection;
import pl.piasta.camperoo.user.query.UserProjection;
import pl.piasta.camperoo.user.query.UserQueryClient;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserFacade userFacade;
    private final UserQueryClient userQueryClient;

    @ResponseStatus(CREATED)
    @PostMapping
    ResourceCreatedDto createCustomerAccount(@RequestBody @Valid CreateAccountDto dto, HttpServletRequest request) {
        String clientIp = HttpRequestUtils.getClientIp(request);
        return userFacade.createAccount(dto, clientIp);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/confirmation")
    void confirmAccount(@RequestBody @Valid ConfirmAccountDto dto) {
        userFacade.confirmAccount(dto);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/status:enable")
    void enableAccount(@PathVariable Long id) {
        userFacade.updateAccountStatus(id, true);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/status:disable")
    void disableAccount(@PathVariable Long id) {
        userFacade.updateAccountStatus(id, false);
    }

    @GetMapping
    Page<UserBasicProjection> getAllOrders(Pageable pageable) {
        return userQueryClient.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    UserProjection getUser(@PathVariable Long id) {
        return userQueryClient.findUserById(id);
    }

    @GetMapping("/self")
    UserProjection getSelfUser(@AuthenticationPrincipal TokenPrincipal principal) {
        return userQueryClient.findUserById(principal.id());
    }
}
