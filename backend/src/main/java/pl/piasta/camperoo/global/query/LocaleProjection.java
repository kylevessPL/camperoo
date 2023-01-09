package pl.piasta.camperoo.global.query;

import org.springframework.beans.factory.annotation.Value;

public interface LocaleProjection {
    Long getId();

    String getName();

    String getCode();

    @Value("#{T(java.util.Objects).requireNonNullElse(target.fallback, false)}")
    boolean isFallback();
}
