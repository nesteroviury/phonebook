package ru.dev.interview.project.phonebook.domain.dao.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressTypeDao;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;
import ru.dev.interview.project.phonebook.domain.repository.AddressTypeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AddressTypeDaoImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";

    private static AddressTypeRepository addressTypeRepository = Mockito.mock(AddressTypeRepository.class);

    @Autowired
    private AddressTypeDao addressTypeDao;

    @BeforeClass
    public static void setUp() {
        AddressType addressType = new AddressType();
        addressType.setId(ID);
        addressType.setValue(VALUE);

        when(addressTypeRepository.findById(ID)).thenReturn(Optional.of(addressType));
        when(addressTypeRepository.save(any(AddressType.class))).thenReturn(addressType);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public AddressTypeDao addressTypeDao() {
            return new AddressTypeDaoImpl(addressTypeRepository);
        }
    }

    @Test
    public void find_persistedAddressTypeById_ReturnAddressType() {
        AddressType addressType = addressTypeDao.find(ID).get();

        assertEquals(ID, addressType.getId());
        assertEquals(VALUE, addressType.getValue());
    }

    @Test
    public void find_notPersistedAddressTypeById_ReturnEmptyOptional() {
        Optional<AddressType> addressType = addressTypeDao.find(2L);

        assertEquals(addressType, Optional.empty());
    }

    @Test
    public void save_notPersistedAddressType_ReturnPersistedAddressTypeWithId() {
        AddressType addressType = new AddressType();
        addressType.setValue(VALUE);
        AddressType persistedAddressType = addressTypeDao.save(addressType);

        assertNull(addressType.getId());
        assertNotNull(persistedAddressType.getId());
        assertEquals(addressType.getValue(), persistedAddressType.getValue());
    }
}
