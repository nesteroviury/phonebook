package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressTypeDao;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.entity.Person;
import ru.dev.interview.project.phonebook.domain.exception.DaoException;
import ru.dev.interview.project.phonebook.domain.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional
public class PersonDaoImpl implements PersonDao {
    private static final long DEFAULT_TYPE_ID = 1L;

    private final AddressDao addressDao;
    private final AddressTypeDao addressTypeDao;
    private final ContactDao contactDao;
    private final ContactTypeDao contactTypeDao;
    private final PersonRepository personRepository;

    @Override
    public void delete(@NonNull Person person) {
        log.debug("call delete(person = {})", person);
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
        log.debug("call find(id = {})", id);
        return personRepository.findById(id);
    }

    @Override
    public List<Person> find(@NonNull String criteria) {
        log.debug("call find(criteria = {})", criteria);
        return personRepository.findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(criteria,
                criteria, criteria, criteria, criteria);
    }

    @Override
    public Person save(@NonNull Person person) throws DaoException {
        log.debug("call save(person = {})", person);
        Address address = person.getAddress();
        address.setType(addressTypeDao.find(DEFAULT_TYPE_ID).orElseThrow(() -> new DaoException(String.format(DaoException.ENTITY_NOT_FOUND, DEFAULT_TYPE_ID))));
        Contact contact = person.getContact();
        contact.setType(contactTypeDao.find(DEFAULT_TYPE_ID).orElseThrow(() -> new DaoException(String.format(DaoException.ENTITY_NOT_FOUND, DEFAULT_TYPE_ID))));
        addressDao.save(address);
        contactDao.save(contact);
        return personRepository.save(person);
    }
}
