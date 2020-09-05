package ru.dev.interview.project.phonebook.domain.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.repository.ContactTypeRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Transactional
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
