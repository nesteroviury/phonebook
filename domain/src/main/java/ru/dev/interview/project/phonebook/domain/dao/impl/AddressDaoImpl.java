package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.repository.AddressRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {
    private final AddressRepository addressRepository;

    @Override
    public Optional<Address> find(@NonNull Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address save(@NonNull Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(@NonNull Address address) {
        return addressRepository.save(address);
    }
}
