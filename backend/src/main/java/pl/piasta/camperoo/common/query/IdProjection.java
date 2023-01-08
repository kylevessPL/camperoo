package pl.piasta.camperoo.common.query;

import java.util.Collection;
import java.util.stream.Collectors;

public interface IdProjection {
    Long getId();

    static Collection<Long> retrieveAllIds(Collection<IdProjection> projections) {
        return projections.stream()
                .map(IdProjection::getId)
                .collect(Collectors.toSet());
    }
}
