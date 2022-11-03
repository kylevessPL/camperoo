package pl.piasta.camperoo.common.domain;

import java.util.List;

public interface Repository<T, I> {
    T save(T entity);

    T get(I id);

    List<T> getAll();

    void delete(I id);
}


