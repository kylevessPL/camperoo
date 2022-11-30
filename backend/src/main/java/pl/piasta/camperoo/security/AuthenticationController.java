package pl.piasta.camperoo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.security.domain.AuthenticationFacade;
import pl.piasta.camperoo.security.dto.LoginResultDto;
import pl.piasta.camperoo.user.dto.PasswordRecoveryDto;
import pl.piasta.camperoo.user.dto.PasswordRecoveryInitDto;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
class AuthenticationController {
    private final AuthenticationFacade authenticationFacade;
    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationProvider authProvider;

    @PostMapping("/refresh-token")
    LoginResultDto refreshToken(
            @AuthenticationPrincipal TokenPrincipal principal,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var user = userDetailsService.loadUserByUsername(principal.email());
        principal = TokenPrincipal.ofUser((AuthenticatedUserDetails) user);
        var authResult = authProvider.createAuthResult(principal, request.getServerName(), request.isSecure());
        response.addHeader(HttpHeaders.SET_COOKIE, authResult.getValue().toString());
        return authResult.getKey();
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/password-recovery")
    void recoverPassword(@RequestBody @Valid PasswordRecoveryDto dto) {
        authenticationFacade.recoverPassword(dto);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/password-recovery/init")
    void initPasswordRecovery(@RequestBody @Valid PasswordRecoveryInitDto dto) {
        authenticationFacade.generatePasswordRecoveryToken(dto);
    }
}
