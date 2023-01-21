package pl.piasta.camperoo.global.query;

import org.springframework.beans.factory.annotation.Value;

public interface LocaleProjection {
    Long getId();

    String getName();

    String getLanguageCode();

    String getAlpha2Code();

    String getAlpha3Code();

    String getNumericCode();

    String getCallingCode();

    @Value("#{T(java.util.Objects).requireNonNullElse(target.fallback, false)}")
    boolean isFallback();
}
