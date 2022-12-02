package pl.piasta.camperoo.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.order.domain.Order;
import pl.piasta.camperoo.user.exception.AccountWithoutRoleException;
import pl.piasta.camperoo.user.exception.UserAccountDisabledException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "gen_users_id", sequenceName = "seq_users_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_users_id")
    private Long id;

    @Embedded
    @Column(nullable = false)
    private EmailAddress emailAddress;

    @Column(nullable = false, length = 73)
    private String passwordHash;

    @Column(nullable = false)
    private boolean active;

    @OneToOne(mappedBy = "user")
    private Person person;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private Set<Role> roles = new HashSet<>();

    @Builder.Default
    @OrderBy("placementDate DESC")
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public void changePasswordHash(String passwordHash) {
        checkIfEnabled();
        this.passwordHash = passwordHash;
    }

    public void disableAccount() {
        checkIfEnabled();
        active = false;
    }

    public void changeRoles(Role... roles) {
        checkIfEnabled();
        checkIfRoleProvided(roles);
        Set<Role> roleSet = Set.of(roles);
        this.roles.retainAll(roleSet);
        this.roles.addAll(roleSet);
    }

    private void checkIfEnabled() {
        if (!active) {
            throw new UserAccountDisabledException(id);
        }
    }

    private static void checkIfRoleProvided(Role[] roles) {
        if (ArrayUtils.isEmpty(roles)) {
            throw new AccountWithoutRoleException();
        }
    }
}
