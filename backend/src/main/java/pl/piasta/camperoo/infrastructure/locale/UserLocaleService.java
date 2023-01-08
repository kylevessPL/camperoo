package pl.piasta.camperoo.infrastructure.locale;

import org.springframework.context.i18n.LocaleContextHolder;
import pl.piasta.camperoo.common.handler.UserLocaleHandler;

import java.util.Locale;

class UserLocaleService implements UserLocaleHandler {
    @Override
    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }
}
