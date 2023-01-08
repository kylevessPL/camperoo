package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.file.exception.FileNotFoundException;
import pl.piasta.camperoo.file.query.FileProjection;
import pl.piasta.camperoo.file.query.FileQueryClient;

@RequiredArgsConstructor
class FileDatabaseRepository implements FileQueryClient {
    private final FileJpaRepository repository;

    @Override
    public FileProjection findFileById(Long fileId) {
        return repository
                .findOneById(fileId)
                .orElseThrow(() -> new FileNotFoundException(fileId));
    }
}

