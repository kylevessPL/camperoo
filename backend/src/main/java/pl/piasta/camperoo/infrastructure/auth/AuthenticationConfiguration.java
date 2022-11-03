package pl.piasta.camperoo.infrastructure.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.security.TokenAuthenticationProvider;

@Configuration
class AuthenticationConfiguration {

    @Bean
    TokenAuthenticationProvider tokenAuthenticationProvider(
            @Value("${app.security.jwt.secret}") String jwtSecret,
            @Value("${app.security.jwt.validMinutes}") long jwtValidMinutes
    ) {
        var jwtProvider = new JwtProvider(jwtSecret, jwtValidMinutes);
        return new JwtTokenAuthenticationProvider(jwtProvider);
    }
}
