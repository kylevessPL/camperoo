package pl.piasta.camperoo.infrastructure.locale;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.global.domain.Locale;
import pl.piasta.camperoo.global.domain.LocaleRepository;
import pl.piasta.camperoo.global.query.LocaleProjection;
import pl.piasta.camperoo.global.query.LocaleQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class LocaleDatabaseRepository implements LocaleRepository, LocaleQueryClient {
    private final LocaleJpaRepository repository;

    @Override
    public Locale save(Locale entity) {
        return repository.save(entity);
    }

    @Override
    public Locale getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<Locale> get(Long id) {
        return repository.findById(id);
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
    public List<Locale> getAll() {
        return repository.findAll();
    }

    @Override
    public List<LocaleProjection> findAllLocales() {
        return repository.findAllBy();
    }
}
