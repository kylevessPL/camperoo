package pl.piasta.camperoo.file.domain;

public interface FileProcessor {
    String encodeToBase64(byte[] fileBytes);

    String generateThumbnail(byte[] imageBytes);
}
