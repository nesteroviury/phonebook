package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
    void delete(@NonNull Person person);

    List<Person> find();

    Optional<Person> find(@NonNull Long id);

    List<Person> find(@NonNull String criteria);

    Person save(@NonNull Person person);

    Person update(@NonNull Person person);
}
