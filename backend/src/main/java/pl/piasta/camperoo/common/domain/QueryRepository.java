package pl.piasta.camperoo.common.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface QueryRepository<T, I> {

    <R> R get(I id, Class<R> projection);

    <R> List<R> getAll(Class<R> projection);

    <R> Page<R> getAll(Class<R> projection, Pageable pageable);

    <R> Page<R> getAll(Specification<T> specification, Class<R> projection, Pageable pageable);
}


