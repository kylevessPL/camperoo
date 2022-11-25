package pl.piasta.camperoo.common.domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DescriptionOrientedEntity<T extends AbstractEntity> extends AbstractEntity {
    public abstract String getDescription();

    public abstract T getLocale();
}
