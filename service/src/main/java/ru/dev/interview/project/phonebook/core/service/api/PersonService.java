package ru.dev.interview.project.phonebook.core.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.core.exception.ServiceException;
import ru.dev.interview.project.phonebook.dto.Person;

import java.util.List;

public interface PersonService {
    void delete(@NonNull Person person);

    List<Person> find();

    Person find(@NonNull Long id) throws ServiceException;

    List<Person> find(@NonNull String criteria);

    Person save(@NonNull Person person) throws ServiceException;

    Person update(@NonNull Person person) throws ServiceException;
}
