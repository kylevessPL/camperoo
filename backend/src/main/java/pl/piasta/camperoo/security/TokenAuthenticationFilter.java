package pl.piasta.camperoo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenAuthenticationProvider authProvider;

    public static Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> TokenAuthenticationProvider.ACCESS_TOKEN.equals(cookie.getName()))
                        .findFirst())
                .map(Cookie::getValue);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        extractToken(request)
                .filter(authProvider::isTokenValid)
                .ifPresent(token -> authenticate(token, request));
        chain.doFilter(request, response);
    }

    private void authenticate(String token, HttpServletRequest request) {
        var principal = authProvider.extractAuthenticationPrincipal(token);
        var auth = new UsernamePasswordAuthenticationToken(principal, null, principal.authorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
