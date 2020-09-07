package ru.dev.interview.project.phonebook.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

import java.util.Optional;

public interface AddressTypeService {
    /**
     * Найти тип адреса по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    Optional<AddressType> find(@NonNull Long id);

    /**
     * Сохранить тип адреса
     *
     * @param addressType тип адреса
     * @return
     */
    AddressType save(@NonNull AddressType addressType);
}
