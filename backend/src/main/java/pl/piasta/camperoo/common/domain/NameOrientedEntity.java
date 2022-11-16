package pl.piasta.camperoo.common.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NameOrientedEntity<T extends AbstractEntity> extends AbstractEntity {
    public abstract String getName();

    public abstract T getLocale();
}
