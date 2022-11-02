package pl.piasta.camperoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.piasta.camperoo.user.domain.UserRepository;

import javax.servlet.http.HttpServletResponse;

import static pl.piasta.camperoo.security.TokenAuthenticationProvider.ACCESS_TOKEN;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration {
    private final ObjectMapper objectMapper;
    private final TokenAuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http.cors().and()
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/error").permitAll()
                        .antMatchers(
                                "/auth/reset-password",
                                "/auth/reset-password/init"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new JsonAuthenticationFilter(authenticationManager, objectMapper, authenticationProvider))
                .addFilterBefore(new TokenAuthenticationFilter(authenticationProvider), JsonAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            var cookie = ResponseCookie.from(ACCESS_TOKEN, "")
                                    .path("/")
                                    .domain(request.getServerName())
                                    .maxAge(0)
                                    .secure(request.isSecure())
                                    .build();
                            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                        })
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_NO_CONTENT)
                        )
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                )
                .csrf().disable()
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AuthenticationService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }
}
