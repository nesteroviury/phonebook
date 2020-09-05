package ru.dev.interview.project.phonebook.core.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.interview.project.phonebook.core.services.api.ContactTypeService;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeDao contactTypeDao;

    @Override
    public Optional<ContactType> find(@NonNull Long id) {
        return contactTypeDao.find(id);
    }

    @Override
    public ContactType save(@NonNull ContactType contactType) {
        return contactTypeDao.save(contactType);
    }

    @Override
    public ContactType update(@NonNull ContactType contactType) {
        return contactTypeDao.save(contactType);
    }
}
