package pl.piasta.camperoo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.user.domain.Role;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.domain.UserRepository;
import pl.piasta.camperoo.user.domain.vo.EmailAddress;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(EmailAddress.of(username))
                .filter(User::isActive)
                .map(this::authenticatedUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private AuthenticatedUserDetails authenticatedUser(User account) {
        var id = account.getId();
        var email = account.getEmail().getEmail();
        var password = account.getPasswordHash();
        var roles = account.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet());
        var enabled = account.isActive();
        return new AuthenticatedUserDetails(id, email, password, roles, enabled);
    }
}
