package ru.dev.interview.project.phonebook.client.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.dev.interview.project.phonebook.client.constant.ModelAttribute;
import ru.dev.interview.project.phonebook.client.constant.Url;
import ru.dev.interview.project.phonebook.client.constant.View;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.Arrays;

import static ru.dev.interview.project.phonebook.client.constant.Url.APP_PERSON_LIST;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_ADD_PERSON;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_DELETE_PERSON;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_PERSON_LIST;
import static ru.dev.interview.project.phonebook.client.constant.Url.REST_SEARCH;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    private static final String REDIRECT = "redirect:/";
    private final RestTemplate restTemplate;

    /**
     * Добавить абонента
     *
     * @param person абонент
     * @param model
     * @return
     */
    @PostMapping(path = Url.APP_ADD_PERSON)
    public String add(@org.springframework.web.bind.annotation.ModelAttribute Person person, Model model) {
        restTemplate.exchange(REST_ADD_PERSON, HttpMethod.POST, new HttpEntity<>(person, buildHeaders()), new ParameterizedTypeReference<Person>() {
        });
        initNewPersonModelAttribute(model);
        initPersonListModelAttribute(REST_PERSON_LIST, model);
        return REDIRECT.concat(Url.APP_PERSON_LIST);
    }

    /**
     * Удалить абонента
     *
     * @param person абонент
     * @param model
     * @return
     */
    @PostMapping(path = Url.APP_DELETE_PERSON)
    public String delete(@org.springframework.web.bind.annotation.ModelAttribute Person person, Model model) {
        doRestOperation(REST_DELETE_PERSON, HttpMethod.DELETE, person);
        initNewPersonModelAttribute(model);
        initPersonListModelAttribute(REST_PERSON_LIST, model);
        return REDIRECT.concat(Url.APP_PERSON_LIST);
    }

    /**
     * Найти список абонентов. Поиск выполняется по фамилии, имени, отчеству, номеру и адресу
     *
     * @param criteria условие поиска
     * @param model
     * @return
     */
    @PostMapping(path = Url.APP_SEARCH)
    public String find(@RequestParam String criteria, Model model) {
        initNewPersonModelAttribute(model);
        initPersonListModelAttribute(REST_SEARCH.concat("/").concat(criteria), model);
        return View.PERSON_LIST;
    }

    /**
     * Получить список всех абонентов
     *
     * @param model
     * @return
     */
    @GetMapping(path = APP_PERSON_LIST)
    public String list(Model model) {
        initNewPersonModelAttribute(model);
        initPersonListModelAttribute(REST_PERSON_LIST, model);
        return View.PERSON_LIST;
    }

    /**
     * Сформировать зогловки http запроса
     *
     * @return
     */
    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Вызвать REST API
     *
     * @param url        адрес REST сервиса
     * @param httpMethod
     * @param person     абонент
     */
    private void doRestOperation(@NonNull String url, @NonNull HttpMethod httpMethod, @NonNull Person person) {
        restTemplate.exchange(url, httpMethod, new HttpEntity<>(person, buildHeaders()), new ParameterizedTypeReference<Person>() {});
    }

    /**
     * Установить дефолтного абонент(используется в форме добавления нового абонента) в model
     *
     * @param model
     */
    private void initNewPersonModelAttribute(@NonNull Model model) {
        model.addAttribute(ModelAttribute.NEW_PERSON, new Person());
    }

    /**
     * Установть список абонентов в model
     *
     * @param url   адрес REST сервиса
     * @param model
     */
    private void initPersonListModelAttribute(@NonNull String url, @NonNull Model model) {
        model.addAttribute(ModelAttribute.PERSON_LIST, Arrays.asList(restTemplate.getForObject(url, Person[].class)));
    }
}
