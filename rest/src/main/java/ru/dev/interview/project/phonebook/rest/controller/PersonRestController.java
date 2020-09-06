package ru.dev.interview.project.phonebook.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;
import ru.dev.interview.project.phonebook.service.api.PersonService;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.List;

import static ru.dev.interview.project.phonebook.rest.controller.constant.Url.ADD_PERSON;
import static ru.dev.interview.project.phonebook.rest.controller.constant.Url.API;
import static ru.dev.interview.project.phonebook.rest.controller.constant.Url.DELETE;
import static ru.dev.interview.project.phonebook.rest.controller.constant.Url.PERSON_LIST;
import static ru.dev.interview.project.phonebook.rest.controller.constant.Url.SEARCH;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonRestController {
    private final PersonService personService;

    @PostMapping(ADD_PERSON)
    public Person add(@RequestBody Person person) {
        try {
            return personService.save(person);
        } catch (ServiceException e) {
            //todo
            return null;
        }
    }

    @DeleteMapping(DELETE)
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }

    @GetMapping(path=PERSON_LIST)
    public List<Person> find() {
        return personService.find();
    }

    @GetMapping(SEARCH)
    public List<Person> find(@PathVariable String criteria) {
        return personService.find(criteria);
    }
}
