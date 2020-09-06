package ru.dev.interview.project.phonebook.domain.dao.api;

import lombok.NonNull;
import ru.dev.interview.project.phonebook.domain.entity.Address;

import java.util.Optional;

public interface AddressDao {
    Optional<Address> find(@NonNull Long id);

    Address save(@NonNull Address address);
}
