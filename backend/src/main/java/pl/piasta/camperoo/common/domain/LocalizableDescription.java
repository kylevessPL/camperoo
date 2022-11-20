package pl.piasta.camperoo.common.domain;

import pl.piasta.camperoo.common.util.LocalizationUtils;
import pl.piasta.camperoo.global.domain.Locale;

import java.util.Set;

public interface LocalizableDescription<T extends DescriptionOrientedEntity<Locale>> {
    Set<T> getDescriptions();

    default T getLocalizedDescription(java.util.Locale locale) {
        return getDescriptions()
                .stream()
                .sorted(LocalizationUtils.DESCRIPTION_DEFAULT_LAST_COMPARATOR)
                .filter(e -> LocalizationUtils.ofLocaleOrDefault(e, locale))
                .findFirst()
                .orElse(null);
    }
}
