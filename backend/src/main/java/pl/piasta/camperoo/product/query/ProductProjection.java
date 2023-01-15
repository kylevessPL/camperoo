package pl.piasta.camperoo.product.query;

import org.springframework.beans.factory.annotation.Value;
import pl.piasta.camperoo.common.query.DescriptionProjection;

public interface ProductProjection extends ProductBasicProjection, DescriptionProjection {
    ImageProjection getImage();

    interface ImageProjection extends ImageBasicProjection {
        @Value("#{@imageProcessor.encodeToBase64GZip(target.content)}")
        String getContent();
    }
}
