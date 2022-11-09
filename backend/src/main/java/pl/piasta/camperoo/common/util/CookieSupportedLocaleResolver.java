package pl.piasta.camperoo.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleTimeZoneAwareLocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import pl.piasta.camperoo.global.domain.LocaleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class CookieSupportedLocaleResolver extends CookieLocaleResolver {
    private final Locale defaultLocale;
    private final Set<Locale> supportedLocale;

    public CookieSupportedLocaleResolver(@NonNull LocaleRepository localeRepository) {
        this.setCookieName("locale");
        var locales = localeRepository.getAll()
                .stream()
                .map(e -> Map.entry(Locale.forLanguageTag(e.getCode()), e.isFallback()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        this.supportedLocale = getSupportedLocales(locales);
        this.defaultLocale = getDefaultLocale(locales);
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
    private Set<Locale> getSupportedLocales(Map<Locale, Boolean> locale) {
        return locale.keySet();
    }

    @NonNull
    private Locale getDefaultLocale(Map<Locale, Boolean> locale) {
        return locale.entrySet()
                .stream()
                .filter(Entry::getValue)
                .map(Entry::getKey)
                .findFirst()
                .orElse(Locale.US);
    }

    @NonNull
    private Locale resolveSupportedLocale(Locale locale) {
        return supportedLocale.stream()
                .filter(e -> e.equals(locale))
                .findFirst()
                .orElse(defaultLocale);
    }
}
