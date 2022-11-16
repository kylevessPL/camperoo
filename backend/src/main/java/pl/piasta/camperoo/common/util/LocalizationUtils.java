package pl.piasta.camperoo.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;
import pl.piasta.camperoo.common.domain.DescriptionOrientedEntity;
import pl.piasta.camperoo.common.domain.NameOrientedEntity;
import pl.piasta.camperoo.global.domain.Locale;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

@UtilityClass
public class LocalizationUtils {
    public final Comparator<NameOrientedEntity<Locale>> NAME_DEFAULT_LAST_COMPARATOR =
            compareBoolean(name -> name.getLocale().isFallback());

    public final Comparator<DescriptionOrientedEntity<Locale>> DESCRIPTION_DEFAULT_LAST_COMPARATOR =
            compareBoolean(description -> description.getLocale().isFallback());

    private <T> Comparator<T> compareBoolean(Function<T, Boolean> extractor) {
        return Comparator.comparing(extractor, Comparator.reverseOrder());
    }

    public <T extends NameOrientedEntity<Locale>> boolean currentLocaleFilter(T name) {
        return currentLocaleFilter(name.getLocale());
    }

    public <T extends DescriptionOrientedEntity<Locale>> boolean currentLocaleFilter(T description) {
        return currentLocaleFilter(description.getLocale());
    }

    private boolean currentLocaleFilter(Locale locale) {
        var compared = java.util.Locale.forLanguageTag(locale.getCode());
        return locale.isFallback() || Objects.equals(compared, LocaleContextHolder.getLocale());
    }
}
