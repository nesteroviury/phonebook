package ru.dev.interview.project.phonebook.domain.dao.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.ContactDao;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.repository.ContactRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContactDaoImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";

    private static ContactRepository contactRepository = Mockito.mock(ContactRepository.class);

    @Autowired
    private ContactDao contactDao;

    @BeforeClass
    public static void setUp() {
        Contact contact = new Contact();
        contact.setId(ID);
        contact.setValue(VALUE);

        when(contactRepository.findById(ID)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public ContactDao contactDao() {
            return new ContactDaoImpl(contactRepository);
        }
    }

    @Test
    public void find_persistedContactById_ReturnContact() {
        Contact contact = contactDao.find(ID).get();

        assertEquals(ID, contact.getId());
        assertEquals(VALUE, contact.getValue());
    }

    @Test
    public void find_persistedContactById_ReturnEmptyOptional() {
        Optional<Contact> optionalContact = contactDao.find(2L);

        assertEquals(optionalContact, Optional.empty());
    }

    @Test
    public void save_notPersistedContact_ReturnPersistedContactWithId() {
        Contact contact = new Contact();
        contact.setValue(VALUE);
        Contact persistedContact = contactDao.save(contact);

        assertNull(contact.getId());
        assertNotNull(persistedContact.getId());
        assertEquals(contact.getValue(), persistedContact.getValue());
    }
}
