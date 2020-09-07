package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Address;

import java.util.Optional;

public interface AddressDao {
    /**
     * Найти адрес по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    Optional<Address> find(@NonNull Long id);

    /**
     * Сохранить адрес
     *
     * @param address адрес
     * @return
     */
    Address save(@NonNull Address address);
}
