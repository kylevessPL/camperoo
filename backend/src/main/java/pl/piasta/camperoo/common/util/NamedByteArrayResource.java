package pl.piasta.camperoo.common.util;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import pl.piasta.camperoo.file.domain.File;

@Getter(onMethod_ = {@Override, @NonNull})
public class NamedByteArrayResource extends ByteArrayResource {
    private final String filename;

    public NamedByteArrayResource(byte[] byteArray, @NotNull String filename) {
        super(byteArray);
        this.filename = filename;
    }

    public static NamedByteArrayResource fromFile(File file) {
        return new NamedByteArrayResource(file.getContent(), file.getName());
    }
}


