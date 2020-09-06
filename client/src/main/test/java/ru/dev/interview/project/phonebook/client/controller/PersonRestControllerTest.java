package ru.dev.interview.project.phonebook.client.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.client.RestTemplate;
import ru.dev.interview.project.phonebook.client.constant.View;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static ru.dev.interview.project.phonebook.client.constant.ModelAttribute.NEW_PERSON;
import static ru.dev.interview.project.phonebook.client.constant.ModelAttribute.PERSON_LIST;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_ADD_PERSON;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_DELETE_PERSON;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_PERSON_LIST;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_SEARCH;

@RunWith(SpringRunner.class)
public class PersonRestControllerTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";
    private static final String REDIRECT_TO_PERSON_LIST = "redirect:/phone-book/list";
    private static final String VALID_CRITERIA = "criteria";
    private static final String INVALID_CRITERIA = "icriteria";
    private static final ParameterizedTypeReference<Person> EXPECTED_RESPONSE_TYPE = new ParameterizedTypeReference<Person>() {
    };

    private static Person personDtoWithId;
    private static Person personDtoWithoutId;

    private static RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    @Autowired
    private PersonController personController;

    private Model model;
    private String expectedUrl;

    @BeforeClass
    public static void setUp() {
        personDtoWithId = buildPersonDto(ID, VALUE);
        personDtoWithoutId = buildPersonDto(null, VALUE);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public PersonController personController() {
            return new PersonController(restTemplate);
        }
    }

    @Before
    public void init() {
        reset(restTemplate);
        model = new BindingAwareModelMap();
        when(restTemplate.getForObject(REST_PERSON_LIST, Person[].class)).thenReturn(buildPersonArray());
    }

    @Test
    public void add_notPersistedPerson_SavePersonGetPersonListSetNewPersonAndPersonListToModelRedirectToPersonListUrl() {
        expectedUrl = personController.add(personDtoWithoutId, model);

        verify(restTemplate, times(1)).exchange(REST_ADD_PERSON, POST,
                new HttpEntity<>(personDtoWithoutId, buildHeaders()), EXPECTED_RESPONSE_TYPE);
        verify(restTemplate, times(1)).getForObject(REST_PERSON_LIST, Person[].class);
        validateNewPersonAndPersonListModelAttributes(model);
        assertEquals(REDIRECT_TO_PERSON_LIST, expectedUrl);
    }

    @Test
    public void delete_persistedPerson_DeletePersonGetPersonListSetNewPersonAndPersonListToModelRedirectToPersonListUrl() {
        expectedUrl = personController.delete(personDtoWithId, model);

        verify(restTemplate, times(1)).exchange(REST_DELETE_PERSON, DELETE,
                new HttpEntity<>(personDtoWithId, buildHeaders()), EXPECTED_RESPONSE_TYPE);
        verify(restTemplate, times(1)).getForObject(REST_PERSON_LIST, Person[].class);
        validateNewPersonAndPersonListModelAttributes(model);
        assertEquals(REDIRECT_TO_PERSON_LIST, expectedUrl);
    }

    @Test
    public void list_GetPersonListSetNewPersonAndPersonListToModelReturnPersonListUrl() {
        expectedUrl = personController.list(model);

        verify(restTemplate, times(1)).getForObject(REST_PERSON_LIST, Person[].class);
        validateNewPersonAndPersonListModelAttributes(model);
        assertEquals(1, ((List) model.getAttribute(PERSON_LIST)).size());
        assertEquals(View.PERSON_LIST, expectedUrl);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaSuccess_ReturnPersistedPersonList() {
        String restUrl = REST_SEARCH.concat("/").concat(VALID_CRITERIA);
        when(restTemplate.getForObject(restUrl, Person[].class)).thenReturn(buildPersonArray());

        expectedUrl = personController.find(VALID_CRITERIA, model);

        verify(restTemplate, times(1)).getForObject(restUrl, Person[].class);
        validateNewPersonAndPersonListModelAttributes(model);
        assertEquals(1, ((List) model.getAttribute(PERSON_LIST)).size());
        assertEquals(View.PERSON_LIST, expectedUrl);
    }

    @Test
    public void find_persistedPersonsBySearchCriteriaFail_ReturnEmptyPersonList() {
        String restUrl = REST_SEARCH.concat("/").concat(INVALID_CRITERIA);
        when(restTemplate.getForObject(restUrl, Person[].class)).thenReturn(new Person[0]);

        expectedUrl = personController.find(INVALID_CRITERIA, model);

        verify(restTemplate, times(1)).getForObject(restUrl, Person[].class);
        validateNewPersonAndPersonListModelAttributes(model);
        assertEquals(0, ((List) model.getAttribute(PERSON_LIST)).size());
        assertEquals(View.PERSON_LIST, expectedUrl);
    }

    private static Person buildPersonDto(Long id, String value) {
        ru.dev.interview.project.phonebook.dto.Address addressDto = new ru.dev.interview.project.phonebook.dto.Address();
        addressDto.setId(id);
        addressDto.setValue(value);
        ru.dev.interview.project.phonebook.dto.Contact contactDto = new ru.dev.interview.project.phonebook.dto.Contact();
        contactDto.setId(id);
        contactDto.setValue(value);
        return Person.builder()
                .address(addressDto)
                .contact(contactDto)
                .firstName(value)
                .id(id)
                .lastName(value)
                .middleName(value)
                .build();
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private Person[] buildPersonArray() {
        Person[] persons = new Person[1];
        persons[0] = personDtoWithId;
        return persons;
    }

    private void validateNewPersonAndPersonListModelAttributes(Model model) {
        assertTrue(this.model.containsAttribute(NEW_PERSON));
        assertTrue(this.model.containsAttribute(PERSON_LIST));
    }
}
