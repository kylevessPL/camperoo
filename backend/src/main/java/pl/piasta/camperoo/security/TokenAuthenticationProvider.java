package pl.piasta.camperoo.security;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseCookie;
import pl.piasta.camperoo.security.dto.LoginResultDto;

public interface TokenAuthenticationProvider {
    String ACCESS_TOKEN = "access_token";

    TokenPrincipal extractAuthenticationPrincipal(String token);

    boolean isTokenValid(String token);

    Pair<LoginResultDto, ResponseCookie> createAuthResult(
            TokenPrincipal tokenPrincipal,
            String domain,
            boolean secure
    );
}
