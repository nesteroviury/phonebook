package ru.dev.interview.project.phonebook.rest.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.dto.Person;
import ru.dev.interview.project.phonebook.service.api.PersonService;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonRestControllerTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";
    private static final String VALID_CRITERIA = "criteria";
    private static final String INVALID_CRITERIA = "icriteria";

    private static Person personDtoWithId;
    private static Person personDtoWithoutId;

    private static PersonService personService = Mockito.mock(PersonService.class);

    @Autowired
    private PersonRestController personRestController;

    @BeforeClass
    public static void setUp() {
        personDtoWithId = buildPersonDto(ID, VALUE);
        personDtoWithoutId = buildPersonDto(null, VALUE);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public PersonRestController personRestController() {
            return new PersonRestController(personService);
        }
    }

    @Test
    public void add_notPersistedPerson_ReturnPersistedPerson() throws ServiceException {
        when(personService.save(personDtoWithoutId)).thenReturn(personDtoWithId);

        ru.dev.interview.project.phonebook.dto.Person persistedPerson = personRestController.add(personDtoWithoutId);

        verify(personService, times(1)).save(any(Person.class));
        assertNull(personDtoWithoutId.getId());
        assertNull(personDtoWithoutId.getAddress().getId());
        assertNull(personDtoWithoutId.getContact().getId());
        assertEquals(ID, persistedPerson.getId());
        assertEquals(ID, persistedPerson.getAddress().getId());
        assertEquals(ID, persistedPerson.getContact().getId());
        reset(personService);
    }

    @Test
    public void delete_persistedPerson_PersonServiceDeleteMethodCalledOnce() {
        doNothing().when(personService).delete(personDtoWithId);

        personRestController.delete(personDtoWithId);

        verify(personService, times(1)).delete(personDtoWithId);
        reset(personService);
    }

    @Test
    public void find_allPersistedPersons_ReturnPersistedPersonList() {
        when(personService.find()).thenReturn(Collections.singletonList(personDtoWithId));

        List<ru.dev.interview.project.phonebook.dto.Person> people = personRestController.find();

        verify(personService, times(1)).find();
        assertEquals(1, people.size());
        reset(personService);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaSuccess_ReturnPersistedPersonList() {
        when(personService.find(VALID_CRITERIA)).thenReturn(Collections.singletonList(personDtoWithId));

        List<ru.dev.interview.project.phonebook.dto.Person> people = personRestController.find(VALID_CRITERIA);

        verify(personService, times(1)).find(VALID_CRITERIA);
        assertEquals(1, people.size());
        reset(personService);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaFail_ReturnEmptyPersonList() {
        when(personService.find(INVALID_CRITERIA)).thenReturn(Collections.emptyList());

        List<ru.dev.interview.project.phonebook.dto.Person> people = personRestController.find(INVALID_CRITERIA);

        verify(personService, times(1)).find(INVALID_CRITERIA);
        assertEquals(0, people.size());
        reset(personService);
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
