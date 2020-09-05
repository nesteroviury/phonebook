package ru.dev.interview.project.phonebook.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.interview.project.phonebook.core.service.api.PersonService;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.List;

import static ru.dev.interview.project.phonebook.rest.constant.RestPath.ADD_PERSON;
import static ru.dev.interview.project.phonebook.rest.constant.RestPath.API;
import static ru.dev.interview.project.phonebook.rest.constant.RestPath.DELETE;
import static ru.dev.interview.project.phonebook.rest.constant.RestPath.PERSON_LIST;
import static ru.dev.interview.project.phonebook.rest.constant.RestPath.SEARCH;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    private final PersonService personService;

    @PutMapping(ADD_PERSON)
    public Person add(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping(DELETE)
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }

    @GetMapping(PERSON_LIST)
    public List<Person> find() {
        return personService.find();
    }

    @GetMapping(SEARCH)
    public List<Person> find(@PathVariable String criteria) {
        return personService.find(criteria);
    }
}
