package pl.piasta.camperoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public JsonAuthenticationFilter(
            AuthenticationManager authenticationManager,
            ObjectMapper objectMapper,
            TokenAuthenticationProvider tokenAuthenticationProvider
    ) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/auth");
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            var principal = TokenPrincipal.ofUser((AuthenticatedUserDetails) authentication.getPrincipal());
            var resultAndCookie = tokenAuthenticationProvider.createAuthResult(
                    principal, Instant.now(), request.getServerName(), request.isSecure()
            );
            response.addHeader(HttpHeaders.SET_COOKIE, resultAndCookie.getValue().toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getWriter(), resultAndCookie.getKey());
        });
        setAuthenticationFailureHandler((request, response, ex) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage())
        );
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!Objects.equals(HttpMethod.POST.name(), request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        UsernamePasswordAuthenticationToken authRequest;
        try {
            var credentials = objectMapper.readValue(request.getReader(), LoginCredentials.class);
            authRequest = new UsernamePasswordAuthenticationToken(credentials.email().trim(), credentials.password());
        } catch (NullPointerException | IOException ignored) {
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        }
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }
}
