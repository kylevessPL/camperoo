package pl.piasta.camperoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import pl.piasta.camperoo.common.util.ErrorHandlingUtils;
import pl.piasta.camperoo.common.util.ResponseCookieUtils;
import pl.piasta.camperoo.user.domain.UserUserRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration extends AbstractSecurityWebApplicationInitializer {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    ObjectMapper objectMapper,
                                    LocaleResolver localeResolver,
                                    AuthenticationManager authManager,
                                    TokenAuthenticationProvider authProvider
    ) throws Exception {
        return http.cors().and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST,
                                "/auth/password-recovery",
                                "/auth/password-recovery/init",
                                "/users",
                                "/users/confirmation"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/global/locales").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new JsonAuthenticationFilter(authManager, objectMapper, authProvider))
                .addFilterBefore(new TokenAuthenticationFilter(authProvider), JsonAuthenticationFilter.class)
                .addFilterBefore(new LocaleRequestContextFilter(localeResolver), TokenAuthenticationFilter.class)
                .addFilterBefore(
                        new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true),
                        ChannelProcessingFilter.class
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            var cookie = ResponseCookieUtils.clearAccessToken(request);
                            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                        })
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_NO_CONTENT)
                        )
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, ex) -> {
                            logger.warn(ex.getMessage(), ex);
                            var path = ErrorHandlingUtils.resolveRequestPath(request);
                            var body = ErrorHandlingUtils.createErrorAttributes(ex, HttpStatus.UNAUTHORIZED, path);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            objectMapper.writeValue(response.getWriter(), body);
                        })
                )
                .csrf().disable()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(
            @Value("${app.security.cors.allow-origin}") String allowedOrigin,
            @Value("${app.security.cors.allow-methods}") String[] allowedMethods,
            @Value("${app.security.cors.allow-headers}") String[] allowedHeaders,
            @Value("${app.security.cors.expose-headers}") String[] exposedHeaders,
            @Value("${app.security.cors.max-age}") long maxAge
    ) {
        var source = new UrlBasedCorsConfigurationSource();
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        corsConfiguration.setAllowedHeaders(List.of(allowedHeaders));
        corsConfiguration.setAllowedMethods(List.of(allowedMethods));
        corsConfiguration.setExposedHeaders(List.of(exposedHeaders));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(maxAge);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(UserUserRepository userRepository) {
        return new AuthenticationService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
