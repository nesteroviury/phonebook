package ru.dev.interview.project.phonebook.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.entity.Person;
import ru.dev.interview.project.phonebook.domain.exception.DaoException;
import ru.dev.interview.project.phonebook.service.api.PersonService;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;
import ru.dev.interview.project.phonebook.service.mapper.PersonMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonServiceImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";
    private static final String VALID_CRITERIA = "criteria";
    private static final String INVALID_CRITERIA = "icriteria";

    private static PersonDao personDao = Mockito.mock(PersonDao.class);
    private static PersonMapper personMapper = Mockito.mock(PersonMapper.class);

    @Autowired
    private PersonService personService;

    @BeforeClass
    public static void setUp() throws DaoException {
        Person personEntity = buildPersonEntity(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDtoWithoutId = buildPersonDto(null, VALUE);

        when(personDao.find()).thenReturn(Collections.singletonList(personEntity));
        when(personDao.find(ID)).thenReturn(Optional.of(personEntity));
        when(personDao.save(any(Person.class))).thenReturn(personEntity);
        when(personDao.find(VALID_CRITERIA)).thenReturn(Collections.singletonList(personEntity));
        when(personDao.find(INVALID_CRITERIA)).thenReturn(Collections.emptyList());
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonServiceImpl(personDao, personMapper);
        }
    }

    @Test
    public void delete_persistedPerson_PersonDaoDeleteMethodCalledOnce() {
        Person person = buildPersonEntity(null, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(null, VALUE);

        when(personMapper.toEntity(personDto)).thenReturn(person);
        doNothing().when(personDao).delete(any(Person.class));
        personService.delete(personDto);

        verify(personMapper, times(1)).toEntity(personDto);
        verify(personDao, times(1)).delete(person);
        reset(personMapper);
    }

    @Test
    public void find_allPersistedPersons_ReturnPersistedPersonList() {
        Person personEntity = buildPersonEntity(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(ID, VALUE);
        when(personMapper.toDto(Collections.singletonList(personEntity))).thenReturn(Collections.singletonList(personDto));

        List<ru.dev.interview.project.phonebook.dto.Person> people = personService.find();

        verify(personMapper, times(1)).toDto(any(List.class));
        verify(personDao, times(1)).find();
        assertEquals(1, people.size());
        reset(personMapper);
    }

    @Test
    public void find_persistedPersonById_ReturnPersistedPerson() throws ServiceException {
        Person personEntity = buildPersonEntity(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(ID, VALUE);
        when(personMapper.toDto(personEntity)).thenReturn(personDto);
        ru.dev.interview.project.phonebook.dto.Person person = personService.find(ID);

        verify(personMapper, times(1)).toDto(any(Person.class));
        verify(personDao, times(1)).find(ID);
        assertEquals(ID, person.getId());
        assertEquals(VALUE, person.getFirstName());
        assertEquals(VALUE, person.getLastName());
        assertEquals(VALUE, person.getMiddleName());
        reset(personMapper);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaSuccess_ReturnPersistedPersonList() {
        Person personEntity = buildPersonEntity(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(ID, VALUE);
        when(personMapper.toDto(Collections.singletonList(personEntity))).thenReturn(Collections.singletonList(personDto));
        List<ru.dev.interview.project.phonebook.dto.Person> people = personService.find(VALID_CRITERIA);

        verify(personMapper, times(1)).toDto(any(List.class));
        verify(personDao, times(1)).find(VALID_CRITERIA);
        assertEquals(1, people.size());
        reset(personMapper);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaFail_ReturnEmptyPersonList() {
        List<ru.dev.interview.project.phonebook.dto.Person> people = personService.find(INVALID_CRITERIA);

        verify(personMapper, times(1)).toDto(any(List.class));
        verify(personDao, times(1)).find(INVALID_CRITERIA);
        assertEquals(0, people.size());
        reset(personMapper);
    }

    @Test
    public void save_notPersistedPerson_ReturnPersistedPerson() throws DaoException, ServiceException {
        Person personEntity = buildPersonEntity(ID, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDto = buildPersonDto(ID, VALUE);
        Person personEntityWithoutId = buildPersonEntity(null, VALUE);
        ru.dev.interview.project.phonebook.dto.Person personDtoWithoutId = buildPersonDto(null, VALUE);
        when(personMapper.toEntity(personDto)).thenReturn(personEntity);
        when(personMapper.toEntity(personDtoWithoutId)).thenReturn(personEntityWithoutId);
        when(personMapper.toDto(personEntity)).thenReturn(personDto);

        ru.dev.interview.project.phonebook.dto.Person persistedPerson = personService.save(personDto);

        verify(personMapper, times(1)).toDto(any(Person.class));
        verify(personMapper, times(1)).toEntity(any(ru.dev.interview.project.phonebook.dto.Person.class));
        verify(personDao, times(1)).save(any(Person.class));
        assertNull(personDtoWithoutId.getId());
        assertNull(personDtoWithoutId.getAddress().getId());
        assertNull(personDtoWithoutId.getContact().getId());
        assertEquals(ID, personDto.getId());
        assertEquals(ID, personDto.getAddress().getId());
        assertEquals(ID, personDto.getContact().getId());
        reset(personMapper);
    }

    private static Person buildPersonEntity(Long id, String value) {
        AddressType addressType = new AddressType();
        addressType.setId(id);
        addressType.setValue(value);
        Address address = new Address();
        address.setId(id);
        address.setValue(value);
        address.setType(addressType);
        ContactType contactType = new ContactType();
        contactType.setId(id);
        contactType.setValue(value);
        Contact contact = new Contact();
        contact.setId(id);
        contact.setValue(value);
        contact.setType(contactType);
        return Person.builder()
                .address(address)
                .contact(contact)
                .firstName(value)
                .id(id)
                .lastName(value)
                .middleName(value)
                .build();
    }

    private static ru.dev.interview.project.phonebook.dto.Person buildPersonDto(Long id, String value) {
        ru.dev.interview.project.phonebook.dto.Address addressDto = new ru.dev.interview.project.phonebook.dto.Address();
        addressDto.setId(id);
        addressDto.setValue(value);
        ru.dev.interview.project.phonebook.dto.Contact contactDto = new ru.dev.interview.project.phonebook.dto.Contact();
        contactDto.setId(id);
        contactDto.setValue(value);
        return ru.dev.interview.project.phonebook.dto.Person.builder()
                .address(addressDto)
                .contact(contactDto)
                .firstName(value)
                .id(id)
                .lastName(value)
                .middleName(value)
                .build();
    }
}
