package pl.piasta.camperoo.global.query;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface LocaleQueryClient {
    List<LocaleProjection> findAllLocales();
}
