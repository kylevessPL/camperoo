package pl.piasta.camperoo.global.domain;

import pl.piasta.camperoo.common.domain.Repository;

import java.util.List;

public interface LocaleRepository extends Repository<Locale, Long> {
    List<Locale> getAll();
}

