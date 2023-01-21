package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import pl.piasta.camperoo.file.domain.FileProcessor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class FileProcessingService implements FileProcessor {
    private final int thumbnailWidth;

    @Override
    public String encodeToBase64(byte[] fileBytes) {
        if (isNull(fileBytes)) {
            return null;
        }
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    @Override
    @SneakyThrows
    public String generateThumbnail(byte[] imageBytes) {
        if (isNull(imageBytes)) {
            return null;
        }
        try (var inputStream = new ByteArrayInputStream(imageBytes); var outputStream = new ByteArrayOutputStream()) {
            Thumbnails.of(inputStream)
                    .width(thumbnailWidth)
                    .toOutputStream(outputStream);
            return encodeToBase64(outputStream.toByteArray());
        }
    }
}
