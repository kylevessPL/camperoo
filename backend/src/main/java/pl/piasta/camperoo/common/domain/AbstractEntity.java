package pl.piasta.camperoo.common.domain;

import jakarta.persistence.Version;

import java.util.Objects;

public abstract class AbstractEntity {
    @Version
    private Long version;

    public abstract Long getId();

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof AbstractEntity entity && Objects.equals(getId(), entity.getId());
    }
}
