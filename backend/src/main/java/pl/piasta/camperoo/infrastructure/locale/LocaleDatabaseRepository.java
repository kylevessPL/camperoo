package pl.piasta.camperoo.infrastructure.locale;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.global.domain.Locale;
import pl.piasta.camperoo.global.domain.LocaleRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class LocaleDatabaseRepository implements LocaleRepository {
    private final LocaleJpaRepository repository;

    @Override
    public Locale save(Locale entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Locale> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Locale> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
