package ru.dev.interview.project.phonebook.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {
}
