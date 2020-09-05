package ru.dev.interview.project.phonebook.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

public interface AddressTypeRepository extends CrudRepository<AddressType, Long> {
}
