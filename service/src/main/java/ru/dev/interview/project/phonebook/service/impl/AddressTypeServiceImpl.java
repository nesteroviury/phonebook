package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.service.api.AddressTypeService;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class AddressTypeServiceImpl implements AddressTypeService {
    private final AddressTypeDao addressTypeDao;

    @Override
    public Optional<AddressType> find(@NonNull Long id) {
        return addressTypeDao.find(id);
    }

    @Override
    public AddressType save(@NonNull AddressType addressType) {
        return addressTypeDao.save(addressType);
    }
}
