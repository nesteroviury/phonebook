package ru.dev.interview.project.phonebook.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Contact;

import java.util.Optional;

public interface ContactService {
    /**
     * Найти контакт по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    Optional<Contact> find(@NonNull Long id);

    /**
     * Сохранить контакт
     *
     * @param contact контакт
     * @return
     */
    Contact save(@NonNull Contact contact);
}
