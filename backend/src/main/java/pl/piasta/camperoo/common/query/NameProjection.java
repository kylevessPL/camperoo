package pl.piasta.camperoo.common.query;

import org.springframework.beans.factory.annotation.Value;

public interface NameProjection {
    @Value("#{target.getLocalizedName(@userLocaleHandler.getCurrentLocale()).name}")
    String getLocalizedName();
}
