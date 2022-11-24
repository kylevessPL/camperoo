package pl.piasta.camperoo.common.domain;

import pl.piasta.camperoo.common.util.LocalizationUtils;
import pl.piasta.camperoo.global.domain.Locale;

import java.util.Set;

public interface LocalizableName<T extends NameOrientedEntity<Locale>> {
    Set<T> getNames();

    default T getLocalizedName(java.util.Locale locale) {
        return getNames()
                .stream()
                .sorted(LocalizationUtils.NAME_DEFAULT_LAST_COMPARATOR)
                .filter(e -> LocalizationUtils.ofLocaleOrDefault(e, locale))
                .findFirst()
                .orElse(null);
    }
}
