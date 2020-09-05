package ru.dev.interview.project.phonebook.core.mapper;

import org.mapstruct.Mapper;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.entity.Person;

import java.util.List;

@Mapper
public interface PersonMapper {
    Person toEntity(ru.dev.interview.project.phonebook.dto.Person person);

    Contact toEntity(ru.dev.interview.project.phonebook.dto.Contact contact);

    Address toEntity(ru.dev.interview.project.phonebook.dto.Address address);

    ru.dev.interview.project.phonebook.dto.Person toDto(Person person);

    List<ru.dev.interview.project.phonebook.dto.Person> toDto(List<Person> person);

    ru.dev.interview.project.phonebook.dto.Contact toDto(Contact contact);

    ru.dev.interview.project.phonebook.dto.Address toDto(Address address);

}
