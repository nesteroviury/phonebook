package ru.dev.interview.project.phonebook.core.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.core.services.api.ContactTypeService;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Transactional
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
