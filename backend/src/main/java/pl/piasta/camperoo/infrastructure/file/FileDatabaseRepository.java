package pl.piasta.camperoo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.file.domain.File;
import pl.piasta.camperoo.file.exception.FileNotFoundException;
import pl.piasta.camperoo.file.query.FileProjection;
import pl.piasta.camperoo.file.query.FileQueryClient;
import pl.piasta.camperoo.order.domain.OrderFileRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class FileDatabaseRepository implements OrderFileRepository, FileQueryClient {
    private final FileJpaRepository repository;

    @Override
    public File save(File file) {
        return repository.save(file);
    }

    @Override
    public File getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<File> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<File> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public FileProjection findFileById(Long fileId) {
        return repository
                .findOneById(fileId)
                .orElseThrow(() -> new FileNotFoundException(fileId));
    }
}

