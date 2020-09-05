package ru.dev.interview.project.phonebook.core.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.core.services.api.PersonService;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.entity.Person;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    @Override
    public void delete(@NonNull Person person) {
        personDao.delete(person);
    }

    @Override
    public Optional<Person> find(@NonNull Long id) {
        return personDao.find(id);
    }

    @Override
    public List<Person> find(@NonNull String criteria) {
        return personDao.find(criteria);
    }

    @Override
    public Person save(@NonNull Person person) {
        return personDao.save(person);
    }

    @Override
    public Person update(@NonNull Person person) {
        return personDao.save(person);
    }
}
