package pl.piasta.camperoo.common.query;

import org.springframework.beans.factory.annotation.Value;

public interface DescriptionProjection {
    @Value("#{target.getLocalizedDescription(@userLocaleHandler.getCurrentLocale()).description}")
    String getLocalizedDescription();
}
