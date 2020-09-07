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
import ru.dev.interview.project.phonebook.dto.Person;
import ru.dev.interview.project.phonebook.service.api.PersonService;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;

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

    /**
     * Добавить абонента
     *
     * @param person абонент
     * @return
     */
    @PostMapping(ADD_PERSON)
    public Person add(@RequestBody Person person) {
        try {
            return personService.save(person);
        } catch (ServiceException e) {
            //todo
            return null;
        }
    }

    /**
     * Удалить абонента
     *
     * @param person абонент
     */
    @DeleteMapping(DELETE)
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }

    /**
     * Получить список всех абонентов
     *
     * @return
     */
    @GetMapping(path = PERSON_LIST)
    public List<Person> find() {
        return personService.find();
    }

    /**
     * Найти список абонентов. Поиск выполняется по фамилии, имени, отчеству, номеру и адресу
     *
     * @param criteria условие поиска
     * @return
     */
    @GetMapping(SEARCH)
    public List<Person> find(@PathVariable String criteria) {
        return personService.find(criteria);
    }
}
