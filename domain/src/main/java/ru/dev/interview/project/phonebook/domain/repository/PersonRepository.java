package ru.dev.interview.project.phonebook.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dev.interview.project.phonebook.domain.entity.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrAddress_ValueContainsOrContact_ValueContains(String firstName,
                                                                                                                             String middleName,
                                                                                                                             String lastName,
                                                                                                                             String contact,
                                                                                                                             String address);
}
