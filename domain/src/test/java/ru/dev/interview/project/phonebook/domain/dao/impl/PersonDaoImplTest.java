package ru.dev.interview.project.phonebook.domain.dao.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressTypeDao;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.dao.api.PersonDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.entity.Person;
import ru.dev.interview.project.phonebook.domain.exception.DaoException;
import ru.dev.interview.project.phonebook.domain.repository.AddressRepository;
import ru.dev.interview.project.phonebook.domain.repository.AddressTypeRepository;
import ru.dev.interview.project.phonebook.domain.repository.ContactRepository;
import ru.dev.interview.project.phonebook.domain.repository.ContactTypeRepository;
import ru.dev.interview.project.phonebook.domain.repository.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonDaoImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";
    private static final String VALID_CRITERIA = "criteria";
    private static final String INVALID_CRITERIA = "icriteria";

    private static AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
    private static AddressTypeRepository addressTypeRepository = Mockito.mock(AddressTypeRepository.class);
    private static ContactTypeRepository contactTypeRepository = Mockito.mock(ContactTypeRepository.class);
    private static ContactRepository contactRepository = Mockito.mock(ContactRepository.class);
    private static PersonRepository personRepository = Mockito.mock(PersonRepository.class);

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private AddressTypeDao addressTypeDao;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private ContactTypeDao contactTypeDao;
    @Autowired
    private PersonDao personDao;

    @BeforeClass
    public static void setUp() {
        AddressType addressType = new AddressType();
        addressType.setId(ID);
        addressType.setValue(VALUE);
        Address address = new Address();
        address.setId(ID);
        address.setValue(VALUE);
        address.setType(addressType);
        ContactType contactType = new ContactType();
        contactType.setId(ID);
        contactType.setValue(VALUE);
        Contact contact = new Contact();
        contact.setId(ID);
        contact.setValue(VALUE);
        contact.setType(contactType);
        Person person = Person.builder()
                .address(address)
                .contact(contact)
                .firstName(VALUE)
                .id(ID)
                .lastName(VALUE)
                .middleName(VALUE)
                .build();

        when(addressTypeRepository.findById(ID)).thenReturn(Optional.of(addressType));

        when(contactTypeRepository.findById(ID)).thenReturn(Optional.of(contactType));

        doNothing().when(personRepository).delete(any(Person.class));
        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));
        when(personRepository.findById(ID)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(personRepository.findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(
                VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA)
        ).thenReturn(Collections.singletonList(person));
        when(personRepository.findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(
                INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA)
        ).thenReturn(Collections.emptyList());
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public AddressDao addressDao() {
            return new AddressDaoImpl(addressRepository);
        }

        @Bean
        public AddressTypeDao addressTypeDao() {
            return new AddressTypeDaoImpl(addressTypeRepository);
        }

        @Bean
        public ContactDao contactDao() {
            return new ContactDaoImpl(contactRepository);
        }

        @Bean
        public ContactTypeDao contactTypeDao() {
            return new ContactTypeDaoImpl(contactTypeRepository);
        }

        @Bean
        public PersonDao personDao() {
            return new PersonDaoImpl(addressDao(), addressTypeDao(), contactDao(), contactTypeDao(), personRepository);
        }
    }

    @Test
    public void delete_persistedPerson_PersonRepositoryDeleteMethodCalledOnce() {
        Person person = new Person();

        personDao.delete(person);

        verify(personRepository, times(1)).delete(person);
    }

    @Test
    public void find_allPersistedPersons_ReturnPersistedPersonList() {
        List<Person> people = personDao.find();

        verify(personRepository, times(1)).findAll();
        assertEquals(1, people.size());
    }

    @Test
    public void find_persistedPersonById_ReturnPersistedPerson() {
        Person person = personDao.find(ID).get();

        verify(personRepository, times(1)).findById(ID);
        assertEquals(ID, person.getId());
        assertEquals(VALUE, person.getFirstName());
        assertEquals(VALUE, person.getLastName());
        assertEquals(VALUE, person.getMiddleName());
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaSuccess_ReturnPersistedPersonList() {
        List<Person> people = personDao.find(VALID_CRITERIA);

        verify(personRepository, times(1)).findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(
                VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA, VALID_CRITERIA);
        assertEquals(1, people.size());
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaFail_ReturnEmptyPersonList() {
        List<Person> people = personDao.find(INVALID_CRITERIA);

        verify(personRepository, times(1)).findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(
                INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA, INVALID_CRITERIA);
        assertEquals(0, people.size());
    }

    @Test
    public void save_notPersistedPerson_ReturnPersistedPerson() throws DaoException {
        Address address = new Address();
        address.setValue(VALUE);
        Contact contact = new Contact();
        contact.setValue(VALUE);
        Person person = Person.builder()
                .address(address)
                .contact(contact)
                .firstName(VALUE)
                .lastName(VALUE)
                .middleName(VALUE)
                .build();

        Person persistedPerson = personDao.save(person);

        verify(personRepository, times(1)).save(person);
        assertNull(person.getId());
        assertNull(person.getAddress().getId());
        assertNull(person.getContact().getId());
        assertEquals(ID, persistedPerson.getId());
        assertEquals(ID, persistedPerson.getAddress().getId());
        assertEquals(ID, persistedPerson.getContact().getId());
    }
}
