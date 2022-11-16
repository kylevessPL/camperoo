package pl.piasta.camperoo.common.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DescriptionOrientedEntity<T extends AbstractEntity> extends AbstractEntity {
    public abstract String getDescription();

    public abstract T getLocale();
}
