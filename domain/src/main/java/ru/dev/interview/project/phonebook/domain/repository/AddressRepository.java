package ru.dev.interview.project.phonebook.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dev.interview.project.phonebook.domain.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
