package pl.piasta.camperoo.common.util;

import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

@Getter(onMethod_ = {@Override, @NonNull})
public class NamedByteArrayResource extends ByteArrayResource {
    private final String filename;

    public NamedByteArrayResource(byte[] byteArray, @NotNull String filename) {
        super(byteArray);
        this.filename = filename;
    }
}


