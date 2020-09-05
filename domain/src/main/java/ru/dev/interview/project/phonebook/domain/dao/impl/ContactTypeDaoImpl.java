package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.repository.ContactTypeRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactTypeDaoImpl implements ContactTypeDao {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public Optional<ContactType> find(@NonNull Long id) {
        return contactTypeRepository.findById(id);
    }

    @Override
    public ContactType save(@NonNull ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Override
    public ContactType update(@NonNull ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }
}
