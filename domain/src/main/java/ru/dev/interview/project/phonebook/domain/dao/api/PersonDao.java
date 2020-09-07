package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Person;
import ru.dev.interview.project.phonebook.domain.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
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
    Optional<Person> find(@NonNull Long id);

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
     * @throws DaoException
     */
    Person save(@NonNull Person person) throws DaoException;
}
