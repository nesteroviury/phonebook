package ru.dev.interview.project.phonebook.core.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Contact;

import java.util.Optional;

public interface ContactService {
    Optional<Contact> find(@NonNull Long id);

    Contact save(@NonNull Contact contact);

    Contact update(@NonNull Contact contact);
}
