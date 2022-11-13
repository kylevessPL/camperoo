package pl.piasta.camperoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.piasta.camperoo.common.util.ErrorHandlingUtils;
import pl.piasta.camperoo.common.util.ResponseCookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String REQUEST_AUTH_PATH = "/auth";
    private final ObjectMapper objectMapper;

    public JsonAuthenticationFilter(
            AuthenticationManager authManager,
            ObjectMapper objectMapper,
            TokenAuthenticationProvider authProvider
    ) {
        super(authManager);
        this.objectMapper = objectMapper;
        setFilterProcessesUrl(REQUEST_AUTH_PATH);
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            var principal = TokenPrincipal.ofUser((AuthenticatedUserDetails) authentication.getPrincipal());
            var authResult = authProvider.createAuthResult(principal, request.getServerName(), request.isSecure());
            response.addHeader(HttpHeaders.SET_COOKIE, authResult.getValue().toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getWriter(), authResult.getKey());
        });
        setAuthenticationFailureHandler((request, response, ex) -> {
            logger.warn(ex.getMessage(), ex);
            var cookie = ResponseCookieUtils.clearAccessToken(request);
            var authResult = ErrorHandlingUtils.createErrorAttributes(ex, HttpStatus.UNAUTHORIZED, REQUEST_AUTH_PATH);
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            objectMapper.writeValue(response.getWriter(), authResult);
        });
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
            authRequest = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        } catch (NullPointerException | IOException ignored) {
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        }
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }
}
