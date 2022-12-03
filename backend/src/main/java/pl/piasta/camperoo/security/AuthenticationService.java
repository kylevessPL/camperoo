package pl.piasta.camperoo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.user.domain.Role;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.domain.UserRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAddress(EmailAddress.of(username))
                .filter(User::isActive)
                .map(this::authenticatedUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private AuthenticatedUserDetails authenticatedUser(User account) {
        var id = account.getId();
        var email = account.getEmailAddress().getEmail();
        var password = account.getPasswordHash();
        var roles = account.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet());
        var enabled = account.isActive();
        return new AuthenticatedUserDetails(id, email, password, roles, enabled);
    }
}
