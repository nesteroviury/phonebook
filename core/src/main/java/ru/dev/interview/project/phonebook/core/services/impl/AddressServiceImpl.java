package ru.dev.interview.project.phonebook.core.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.core.services.api.AddressService;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Override
    public Optional<Address> find(@NonNull Long id) {
        return addressDao.find(id);
    }

    @Override
    public Address save(@NonNull Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address update(@NonNull Address address) {
        return addressDao.save(address);
    }
}
