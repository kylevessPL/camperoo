package pl.piasta.camperoo.user.query;

import pl.piasta.camperoo.common.query.DescriptionProjection;

import java.util.Set;

public interface UserBasicProjection {
    Long getId();

    String getEmail();

    boolean isActive();

    PersonBasicProjection getPerson();

    Set<RoleProjection> getRoles();

    interface RoleProjection extends DescriptionProjection {
        Long getId();

        String getName();
    }

    interface PersonBasicProjection {
        Long getId();

        String getFirstName();

        String getLastName();
    }
}
