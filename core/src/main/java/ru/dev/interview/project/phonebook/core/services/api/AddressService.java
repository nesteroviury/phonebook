package ru.dev.interview.project.phonebook.core.services.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> find(@NonNull Long id);

    Address save(@NonNull Address address);

    Address update(@NonNull Address address);
}
