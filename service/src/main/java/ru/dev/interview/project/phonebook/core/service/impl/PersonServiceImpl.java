package ru.dev.interview.project.phonebook.core.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.core.exception.ServiceException;
import ru.dev.interview.project.phonebook.core.mapper.PersonMapper;
import ru.dev.interview.project.phonebook.core.service.api.PersonService;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;
    private final PersonMapper personMapper;

    @Override
    public void delete(@NonNull Person person) {
        personDao.delete(personMapper.toEntity(person));
    }

    @Override
    public List<Person> find() {
        return personMapper.toDto(personDao.find());
    }

    @Override
    public Person find(@NonNull Long id) throws ServiceException {
        return personMapper.toDto(personDao.find(id).orElseThrow(() -> new ServiceException(String.format(ServiceException.ENTITY_NOT_FOUND, id))));
    }

    @Override
    public List<Person> find(@NonNull String criteria) {
        return personMapper.toDto(personDao.find(criteria));
    }

    @Override
    public Person save(@NonNull Person person) {
        return personMapper.toDto(personDao.save(personMapper.toEntity(person)));
    }

    @Override
    public Person update(@NonNull Person person) {
        return personMapper.toDto(personDao.save(personMapper.toEntity(person)));
    }
}
