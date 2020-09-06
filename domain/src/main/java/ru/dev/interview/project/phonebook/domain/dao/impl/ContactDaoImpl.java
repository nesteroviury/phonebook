package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.repository.ContactRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ContactDaoImpl implements ContactDao {
    private final ContactRepository contactRepository;

    @Override
    public Optional<Contact> find(@NonNull Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact save(@NonNull Contact contact) {
        return contactRepository.save(contact);
    }
}
