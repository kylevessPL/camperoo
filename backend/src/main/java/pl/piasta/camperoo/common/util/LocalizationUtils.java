package pl.piasta.camperoo.common.util;

import lombok.experimental.UtilityClass;
import pl.piasta.camperoo.common.domain.DescriptionOrientedEntity;
import pl.piasta.camperoo.common.domain.NameOrientedEntity;
import pl.piasta.camperoo.global.domain.Locale;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@UtilityClass
public class LocalizationUtils {
    public final Comparator<NameOrientedEntity<Locale>> NAME_DEFAULT_LAST_COMPARATOR =
            compareBoolean(name -> name.getLocale().getFallback());

    public final Comparator<DescriptionOrientedEntity<Locale>> DESCRIPTION_DEFAULT_LAST_COMPARATOR =
            compareBoolean(description -> description.getLocale().getFallback());

    private <T> Comparator<T> compareBoolean(Function<T, Boolean> extractor) {
        return Comparator.comparing(extractor);
    }

    public boolean ofLocaleOrDefault(Map.Entry<java.util.Locale, Boolean> entry, java.util.Locale locale) {
        return isLocaleOrDefault(entry, locale);
    }

    public <T extends NameOrientedEntity<Locale>> boolean ofLocaleOrDefault(T name, java.util.Locale locale) {
        return isLocaleOrDefault(name.getLocale(), locale);
    }

    public <T extends DescriptionOrientedEntity<Locale>> boolean ofLocaleOrDefault(
            T description,
            java.util.Locale locale
    ) {
        return isLocaleOrDefault(description.getLocale(), locale);
    }

    private boolean isLocaleOrDefault(Locale object, java.util.Locale locale) {
        var compared = java.util.Locale.forLanguageTag(object.getCode());
        var fallback = object.getFallback();
        return isLocaleOrDefault(compared, locale, fallback);
    }

    private boolean isLocaleOrDefault(Map.Entry<java.util.Locale, Boolean> object, java.util.Locale locale) {
        var compared = object.getKey();
        var fallback = object.getValue();
        return isLocaleOrDefault(compared, locale, fallback);
    }

    private boolean isLocaleOrDefault(java.util.Locale compared, java.util.Locale locale, boolean fallback) {
        return fallback || Objects.equals(compared, locale);
    }
}
