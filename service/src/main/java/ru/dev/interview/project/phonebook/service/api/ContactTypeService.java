package ru.dev.interview.project.phonebook.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

import java.util.Optional;

public interface ContactTypeService {
    /**
     * Найти тип контакта по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    Optional<ContactType> find(@NonNull Long id);

    /**
     * Сохранить тип контакта
     *
     * @param contactType тип контакта
     * @return
     */
    ContactType save(@NonNull ContactType contactType);
}
