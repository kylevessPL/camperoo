package pl.piasta.camperoo.infrastructure.config;

import org.springframework.context.annotation.Profile;
import pl.piasta.camperoo.util.AppProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile(AppProfiles.LOCAL)
public @interface LocalProfile {
}
