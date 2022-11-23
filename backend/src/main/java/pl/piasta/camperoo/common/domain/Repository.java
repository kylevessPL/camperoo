package pl.piasta.camperoo.common.domain;

import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {
    T save(T entity);

    Optional<T> get(I id);

    List<T> getAll();

    void delete(I id);
}


