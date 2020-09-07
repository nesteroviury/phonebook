package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.service.api.AddressService;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Override
    public Optional<Address> find(@NonNull Long id) {
        log.debug("call find(id = {})", id);
        return addressDao.find(id);
    }

    @Override
    public Address save(@NonNull Address address) {
        log.debug("call save(address = {})", address);
        return addressDao.save(address);
    }
}
