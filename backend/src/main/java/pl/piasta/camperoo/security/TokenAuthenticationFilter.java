package pl.piasta.camperoo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        extractToken(request)
                .filter(authenticationProvider::isTokenValid)
                .ifPresent(token -> authenticate(token, request));
        chain.doFilter(request, response);
    }

    public static Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> TokenAuthenticationProvider.ACCESS_TOKEN.equals(cookie.getName()))
                        .findFirst())
                .map(Cookie::getValue);
    }

    private void authenticate(String token, HttpServletRequest req) {
        var principal = authenticationProvider.extractAuthenticationPrincipal(token);
        var auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
