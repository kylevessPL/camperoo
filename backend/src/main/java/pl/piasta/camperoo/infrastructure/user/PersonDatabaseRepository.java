package pl.piasta.camperoo.infrastructure.user;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.user.domain.Person;
import pl.piasta.camperoo.user.domain.PersonRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class PersonDatabaseRepository implements PersonRepository {
    private final PersonJpaRepository repository;

    @Override
    public Person save(Person entity) {
        return repository.save(entity);
    }

    @Override
    public Person getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<Person> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Person> getAll() {
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
}

