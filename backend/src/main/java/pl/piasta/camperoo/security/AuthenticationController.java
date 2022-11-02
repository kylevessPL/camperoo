package pl.piasta.camperoo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.security.dto.LoginResultDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenAuthenticationProvider authenticationProvider;

    @PostMapping("/refresh-token")
    public LoginResultDto refreshToken(
            @AuthenticationPrincipal TokenPrincipal principal,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var user = authenticationService.loadUserByUsername(principal.email());
        principal = TokenPrincipal.ofUser((AuthenticatedUserDetails) user);
        var resultAndCookie = authenticationProvider.createAuthResult(
                principal, Instant.now(),
                request.getServerName(), request.isSecure()
        );
        var cookie = resultAndCookie.getValue();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return resultAndCookie.getKey();
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PostMapping("/reset-password/init")
//    public void generatePasswordRecoveryCode(@RequestBody @Valid GeneratePasswordRecoveryCode dto) {
//        accountService.generatePasswordRecoveryCode(dto);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PostMapping("/reset-password")
//    public void recoverPassword(@RequestBody @Valid RecoverPassword dto) {
//        accountService.recoverPassword(dto);
//    }
}
