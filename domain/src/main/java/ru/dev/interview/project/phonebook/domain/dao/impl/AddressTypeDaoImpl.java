package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;
import ru.dev.interview.project.phonebook.domain.repository.AddressTypeRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressTypeDaoImpl implements AddressTypeDao {
    private final AddressTypeRepository addressTypeRepository;

    @Override
    public Optional<AddressType> find(@NonNull Long id) {
        return addressTypeRepository.findById(id);
    }

    @Override
    public AddressType save(@NonNull AddressType addressType) {
        return addressTypeRepository.save(addressType);
    }
}
