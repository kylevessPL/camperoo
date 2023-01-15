package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import pl.piasta.camperoo.file.domain.FileProcessor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class FileProcessingService implements FileProcessor {
    private final int thumbnailHeight;

    @Override
    public String encodeToBase64GZip(byte[] fileBytes) {
        if (isNull(fileBytes)) {
            return null;
        }
        var gzip = encodeToGZip(fileBytes);
        return Base64.getEncoder().encodeToString(gzip);
    }

    @Override
    @SneakyThrows
    public String generateThumbnail(byte[] imageBytes) {
        if (isNull(imageBytes)) {
            return null;
        }
        try (var inputStream = new ByteArrayInputStream(imageBytes); var outputStream = new ByteArrayOutputStream()) {
            Thumbnails.of(inputStream)
                    .height(thumbnailHeight)
                    .toOutputStream(outputStream);
            return encodeToBase64GZip(outputStream.toByteArray());
        }
    }

    @SneakyThrows
    private byte[] encodeToGZip(byte[] fileBytes) {
        try (var outputStream = new ByteArrayOutputStream(); var gzip = new GZIPOutputStream(outputStream)) {
            gzip.write(fileBytes);
            gzip.finish();
            return outputStream.toByteArray();
        }
    }
}
