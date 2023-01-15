package pl.piasta.camperoo.common.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleTimeZoneAwareLocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import pl.piasta.camperoo.common.util.LocalizationUtils;
import pl.piasta.camperoo.global.domain.LocaleRepository;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class CookieSupportedLocaleResolver extends CookieLocaleResolver {
    private final Map<Locale, Boolean> supportedLocale;

    public CookieSupportedLocaleResolver(@NonNull LocaleRepository localeRepository) {
        super("locale");
        this.supportedLocale = getSupportedLocale(localeRepository);
    }

    @Override
    @NonNull
    public Locale resolveLocale(@NonNull HttpServletRequest request) {
        var locale = super.resolveLocale(request);
        return resolveSupportedLocale(locale);
    }

    @Override
    @NonNull
    public LocaleContext resolveLocaleContext(@NonNull HttpServletRequest request) {
        var localeContext = (TimeZoneAwareLocaleContext) super.resolveLocaleContext(request);
        var locale = resolveSupportedLocale(localeContext.getLocale());
        var timeZone = localeContext.getTimeZone();
        return new SimpleTimeZoneAwareLocaleContext(locale, timeZone);
    }

    @NonNull
    private Map<Locale, Boolean> getSupportedLocale(LocaleRepository localeRepository) {
        return localeRepository.getAll()
                .stream()
                .map(e -> Map.entry(Locale.forLanguageTag(e.getCode()), e.getFallback()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    @NonNull
    private Locale resolveSupportedLocale(Locale locale) {
        return supportedLocale.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .filter(e -> LocalizationUtils.ofLocaleOrDefault(e, locale))
                .map(Entry::getKey)
                .findFirst()
                .orElse(Locale.ENGLISH);
    }
}
