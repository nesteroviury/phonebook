package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

import java.util.Optional;

public interface AddressTypeDao {
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
