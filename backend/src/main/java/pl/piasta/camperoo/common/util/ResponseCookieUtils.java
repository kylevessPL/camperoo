package pl.piasta.camperoo.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseCookie;

import java.time.Duration;

import static pl.piasta.camperoo.security.TokenAuthenticationProvider.ACCESS_TOKEN;

@UtilityClass
public class ResponseCookieUtils {
    public ResponseCookie clearAccessToken(HttpServletRequest request) {
        return accessToken(StringUtils.EMPTY, Duration.ZERO, request.getServerName(), request.isSecure());
    }

    public ResponseCookie accessToken(String value, Duration maxAge, String domain, boolean secure) {
        return ResponseCookie.from(ACCESS_TOKEN, value)
                .path("/")
                .domain(domain)
                .maxAge(maxAge)
                .httpOnly(true)
                .secure(secure)
                .sameSite("Strict")
                .build();
    }
}
