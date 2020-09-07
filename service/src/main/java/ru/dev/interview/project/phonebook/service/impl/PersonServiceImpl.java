package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.exception.DaoException;
import ru.dev.interview.project.phonebook.dto.Person;
import ru.dev.interview.project.phonebook.service.api.PersonService;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;
import ru.dev.interview.project.phonebook.service.mapper.PersonMapper;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;
    private final PersonMapper personMapper;

    @Override
    public void delete(@NonNull Person person) {
        log.debug("call delete(person = {})", person);
        personDao.delete(personMapper.toEntity(person));
    }

    @Override
    public List<Person> find() {
        return personMapper.toDto(personDao.find());
    }

    @Override
    public Person find(@NonNull Long id) throws ServiceException {
        log.debug("call find(id = {})", id);
        return personMapper.toDto(personDao.find(id).orElseThrow(() -> new ServiceException(String.format(ServiceException.ENTITY_NOT_FOUND, id))));
    }

    @Override
    public List<Person> find(@NonNull String criteria) {
        log.debug("call find(criteria = {})", criteria);
        return personMapper.toDto(personDao.find(criteria));
    }

    @Override
    public Person save(@NonNull Person person) throws ServiceException {
        log.debug("call save(person = {})", person);
        try {
            return personMapper.toDto(personDao.save(personMapper.toEntity(person)));
        } catch (DaoException e) {
            String errorMsg = "Error while person save operation: ";
            log.warn(errorMsg, e);
            throw new ServiceException(errorMsg, e);
        }
    }
}
