package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.repository.AddressRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
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
}
