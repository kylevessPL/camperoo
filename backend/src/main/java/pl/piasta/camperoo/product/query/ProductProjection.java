package pl.piasta.camperoo.product.query;

import org.springframework.beans.factory.annotation.Value;

public interface ProductProjection extends ProductBasicProjection {
    ImageProjection getImage();

    interface ImageProjection extends ImageBasicProjection {
        @Value("#{@imageProcessor.encodeToBase64(target.content)}")
        String getContent();
    }
}
