package pl.piasta.camperoo.file.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.file.domain.File;

public class FileNotFoundException extends NotFoundException {
    public FileNotFoundException(Long id) {
        super(File.class, "id", id, ErrorCode.FILE_NOT_FOUND, ErrorProperty.FILE_NOT_FOUND);
    }
}


