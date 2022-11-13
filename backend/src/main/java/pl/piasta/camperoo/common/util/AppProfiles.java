package pl.piasta.camperoo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppProfiles {
    public static final String LOCAL = "local";

    @Documented
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Profile(LOCAL)
    public @interface LocalProfile {
    }
}
