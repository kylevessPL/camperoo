package pl.piasta.camperoo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.security.dto.LoginResultDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
class AuthenticationController {
    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationProvider authProvider;

    @PostMapping("/refresh-token")
    public LoginResultDto refreshToken(
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
