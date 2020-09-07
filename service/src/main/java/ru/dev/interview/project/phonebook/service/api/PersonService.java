package ru.dev.interview.project.phonebook.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.dto.Person;
import ru.dev.interview.project.phonebook.service.exception.ServiceException;

import java.util.List;

public interface PersonService {
    /**
     * Удалить абонента
     *
     * @param person абонент
     */
    void delete(@NonNull Person person);

    /**
     * Получить список всех абонентов
     *
     * @return
     */
    List<Person> find();

    /**
     * Найти абонента по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    Person find(@NonNull Long id) throws ServiceException;

    /**
     * Найти список абонентов. Поиск выполняется по фамилии, имени, отчеству, номеру и адресу
     *
     * @param criteria условие поиска
     * @return
     */
    List<Person> find(@NonNull String criteria);

    /**
     * Сохранить абонента
     *
     * @param person абонент
     * @return
     * @throws ServiceException
     */
    Person save(@NonNull Person person) throws ServiceException;
}
