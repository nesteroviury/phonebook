package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.service.api.ContactService;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    @Override
    public Optional<Contact> find(@NonNull Long id) {
        log.debug("call find(id = {})", id);
        return contactDao.find(id);
    }

    @Override
    public Contact save(@NonNull Contact contact) {
        log.debug("call save(contact = {})", contact);
        return contactDao.save(contact);
    }
}
