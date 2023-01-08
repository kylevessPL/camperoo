package pl.piasta.camperoo.file.domain;

public interface FileProcessor {
    String encodeToBase64GZip(byte[] fileBytes);

    String generateThumbnail(byte[] imageBytes);
}
