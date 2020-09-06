package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

import java.util.Optional;

public interface ContactTypeDao {
    Optional<ContactType> find(@NonNull Long id);

    ContactType save(@NonNull ContactType contactType);
}
