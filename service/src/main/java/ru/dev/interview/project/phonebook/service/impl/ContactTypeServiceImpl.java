package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.service.api.ContactTypeService;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
@Transactional
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeDao contactTypeDao;

    @Override
    public Optional<ContactType> find(@NonNull Long id) {
        log.debug("call find(id = {})", id);
        return contactTypeDao.find(id);
    }

    @Override
    public ContactType save(@NonNull ContactType contactType) {
        log.debug("call save(contactType = {})", contactType);
        return contactTypeDao.save(contactType);
    }
}
