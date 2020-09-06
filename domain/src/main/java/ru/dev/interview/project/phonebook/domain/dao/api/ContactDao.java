package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Contact;

import java.util.Optional;

public interface ContactDao {
    Optional<Contact> find(@NonNull Long id);

    Contact save(@NonNull Contact contact);
}
