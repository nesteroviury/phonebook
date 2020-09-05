package ru.dev.interview.project.phonebook.core.service.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

import java.util.Optional;

public interface AddressTypeService {
    Optional<AddressType> find(@NonNull Long id);

    AddressType save(@NonNull AddressType addressType);
}
