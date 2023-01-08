package pl.piasta.camperoo.common.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryJpaRepository<I> {

    <R> R findOneById(I id, Class<R> projection);

    <R> List<R> findAllBy(Class<R> projection);

    <R> Page<R> findAllBy(Class<R> projection, Pageable pageable);
}


