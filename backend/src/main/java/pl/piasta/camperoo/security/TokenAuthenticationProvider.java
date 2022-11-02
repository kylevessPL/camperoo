package pl.piasta.camperoo.security;

import org.springframework.http.ResponseCookie;
import pl.piasta.camperoo.security.dto.LoginResultDto;

import java.time.Instant;
import java.util.Map;

public interface TokenAuthenticationProvider {
    String ACCESS_TOKEN = "access_token";

    TokenPrincipal extractAuthenticationPrincipal(String token);

    boolean isTokenValid(String token);

    Map.Entry<LoginResultDto, ResponseCookie> createAuthResult(
            TokenPrincipal tokenPrincipal,
            Instant issuanceTime,
            String domain,
            boolean secure
    );
}
