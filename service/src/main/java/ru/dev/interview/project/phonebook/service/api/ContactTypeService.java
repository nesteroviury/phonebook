package ru.dev.interview.project.phonebook.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

import java.util.Optional;

public interface ContactTypeService {
    Optional<ContactType> find(@NonNull Long id);

    ContactType save(@NonNull ContactType contactType);
}
