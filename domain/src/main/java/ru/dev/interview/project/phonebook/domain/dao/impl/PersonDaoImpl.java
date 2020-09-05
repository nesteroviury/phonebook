package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.entity.Person;
import ru.dev.interview.project.phonebook.domain.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class PersonDaoImpl implements PersonDao {
    private final PersonRepository personRepository;

    @Override
    public void delete(@NonNull Person person) {
        personRepository.delete(person);
    }

    @Override
    public List<Person> find() {
        return StreamSupport
                .stream(personRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> find(@NonNull Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> find(@NonNull String criteria) {
        return personRepository.findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(criteria,
                criteria, criteria, criteria, criteria);
    }

    @Override
    public Person save(@NonNull Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(@NonNull Person person) {
        return personRepository.save(person);
    }
}
