package pl.piasta.camperoo.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseCookie;
import pl.piasta.camperoo.security.TokenAuthenticationProvider;
import pl.piasta.camperoo.security.TokenPrincipal;
import pl.piasta.camperoo.security.dto.LoginResultDto;

import java.time.Duration;

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
    public Pair<LoginResultDto, ResponseCookie> createAuthResult(
            TokenPrincipal tokenPrincipal,
            String domain,
            boolean secure
    ) {
        var token = jwtProvider.createToken(tokenPrincipal);
        var dates = jwtProvider.extractDates(token);
        var issuanceTime = dates.getLeft();
        var expirationTime = dates.getRight();
        var cookie = ResponseCookie.from(ACCESS_TOKEN, token)
                .path("/")
                .domain(domain)
                .maxAge(Duration.between(issuanceTime, expirationTime))
                .httpOnly(true)
                .secure(secure)
                .sameSite("Strict")
                .build();
        var result = LoginResultDto.builder()
                .issuanceTime(issuanceTime.getEpochSecond())
                .expirationTime(expirationTime.getEpochSecond())
                .roles(tokenPrincipal.roles())
                .build();
        return Pair.of(result, cookie);
    }
}
