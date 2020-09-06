package ru.dev.interview.project.phonebook.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.service.api.ContactService;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.entity.Contact;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    @Override
    public Optional<Contact> find(@NonNull Long id) {
        return contactDao.find(id);
    }

    @Override
    public Contact save(@NonNull Contact contact) {
        return contactDao.save(contact);
    }
}
