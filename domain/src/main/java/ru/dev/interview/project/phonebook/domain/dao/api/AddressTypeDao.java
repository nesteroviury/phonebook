package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

import java.util.Optional;

public interface AddressTypeDao {
    Optional<AddressType> find(@NonNull Long id);

    AddressType save(@NonNull AddressType addressType);
}
