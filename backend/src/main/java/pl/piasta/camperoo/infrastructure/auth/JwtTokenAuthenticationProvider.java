package pl.piasta.camperoo.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import pl.piasta.camperoo.security.TokenAuthenticationProvider;
import pl.piasta.camperoo.security.TokenPrincipal;
import pl.piasta.camperoo.security.dto.LoginResultDto;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
class JwtTokenAuthenticationProvider implements TokenAuthenticationProvider {
    private final JwtProvider jwtProvider;

    @Override
    public TokenPrincipal extractAuthenticationPrincipal(String token) {
        return jwtProvider.extractPrincipal(token);
    }

    @Override
    public boolean isTokenValid(String token) {
        return jwtProvider.isValid(token);
    }

    @Override
    public Map.Entry<LoginResultDto, ResponseCookie> createAuthResult(
            TokenPrincipal tokenPrincipal,
            Instant issuanceTime,
            String domain,
            boolean secure
    ) {
        var token = jwtProvider.createToken(tokenPrincipal, issuanceTime);
        var expirationTime = jwtProvider.extractExpirationTime(token);
        return Map.entry(
                new LoginResultDto(expirationTime.getEpochSecond(), tokenPrincipal.roles()),
                ResponseCookie.from(ACCESS_TOKEN, token)
                        .path("/")
                        .domain(domain)
                        .maxAge(Duration.between(issuanceTime, expirationTime))
                        .httpOnly(true)
                        .secure(secure)
                        .sameSite("Strict")
                        .build()
        );
    }
}
