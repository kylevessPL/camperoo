package pl.piasta.camperoo.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.piasta.camperoo.user.domain.vo.RoleName;

import java.util.Set;
import java.util.stream.Collectors;

class AuthenticatedUserDetails extends User {
    @Getter
    private final Long id;

    public AuthenticatedUserDetails(Long id, String email, String password, Set<RoleName> roles, boolean enabled) {
        super(
                email,
                password,
                enabled,
                true,
                true,
                true,
                roles.stream()
                        .map(RoleName::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
        this.id = id;
    }

    public String getEmail() {
        return getUsername();
    }

    public Set<String> getRoles() {
        return getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
