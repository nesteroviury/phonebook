package ru.dev.interview.project.phonebook.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dev.interview.project.phonebook.domain.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
