package pl.piasta.camperoo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record TokenPrincipal(Long id, String email, Set<String> roles) {
    public static TokenPrincipal ofUser(AuthenticatedUserDetails authenticatedUserDetails) {
        return new TokenPrincipal(authenticatedUserDetails.getId(), authenticatedUserDetails.getEmail(),
                authenticatedUserDetails.getRoles());
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toUnmodifiableSet());
    }
}
