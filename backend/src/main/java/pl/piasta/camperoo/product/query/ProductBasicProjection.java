package pl.piasta.camperoo.product.query;

import org.springframework.beans.factory.annotation.Value;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.common.query.NameDescriptionProjection;

import java.math.BigDecimal;

public interface ProductBasicProjection extends NameDescriptionProjection {
    BigDecimal getPrice();

    ConstantProjection getCategory();

    ImageBasicProjection getImage();

    Integer getQuantity();

    interface ImageBasicProjection {
        String getContentType();

        @Value("#{@imageProcessor.generateThumbnail(target.content)}")
        String getContent();
    }
}
