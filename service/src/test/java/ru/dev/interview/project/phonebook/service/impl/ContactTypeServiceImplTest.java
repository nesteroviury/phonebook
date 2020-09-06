package ru.dev.interview.project.phonebook.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactTypeDao;
import ru.dev.interview.project.phonebook.domain.dao.impl.ContactTypeDaoImpl;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.repository.ContactTypeRepository;
import ru.dev.interview.project.phonebook.service.api.ContactTypeService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContactTypeServiceImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";

    private static ContactTypeDao contactTypeDao = Mockito.mock(ContactTypeDao.class);

    @Autowired
    private ContactTypeService contactTypeService;

    @BeforeClass
    public static void setUp() {
        ContactType contactType = new ContactType();
        contactType.setId(ID);
        contactType.setValue(VALUE);

        when(contactTypeDao.find(ID)).thenReturn(Optional.of(contactType));
        when(contactTypeDao.save(any(ContactType.class))).thenReturn(contactType);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public ContactTypeService contactTypeService() {
            return new ContactTypeServiceImpl(contactTypeDao);
        }
    }

    @Test
    public void find_persistedContactTypeById_ReturnContactType() {
        ContactType contactType = contactTypeService.find(ID).get();

        assertEquals(ID, contactType.getId());
        assertEquals(VALUE, contactType.getValue());
    }

    @Test
    public void find_persistedContactTypeById_ReturnEmptyOptional() {
        Optional<ContactType> optionalContactType = contactTypeService.find(2L);

        assertEquals(optionalContactType, Optional.empty());
    }

    @Test
    public void save_notPersistedContactType_ReturnPersistedContactTypeWithId() {
        ContactType contactType = new ContactType();
        contactType.setValue(VALUE);
        ContactType persistedContact = contactTypeService.save(contactType);

        assertNull(contactType.getId());
        assertNotNull(persistedContact.getId());
        assertEquals(contactType.getValue(), persistedContact.getValue());
    }
}
