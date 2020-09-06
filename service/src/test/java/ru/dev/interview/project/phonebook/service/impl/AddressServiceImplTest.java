package ru.dev.interview.project.phonebook.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.interview.project.phonebook.domain.dao.api.AddressDao;
import ru.dev.interview.project.phonebook.domain.dao.impl.AddressDaoImpl;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.service.api.AddressService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AddressServiceImplTest {
    private static final Long ID = 1L;
    private static final String VALUE = "value";

    private static AddressDao addressDao = Mockito.mock(AddressDao.class);

    @Autowired
    private AddressService addressService;

    @BeforeClass
    public static void setUp() {
        Address address = new Address();
        address.setId(ID);
        address.setValue(VALUE);

        when(addressDao.find(ID)).thenReturn(Optional.of(address));
        when(addressDao.save(any(Address.class))).thenReturn(address);
    }

    @TestConfiguration
    static class ContextConfiguration {
        @Bean
        public AddressService addressService() {
            return new AddressServiceImpl(addressDao);
        }
    }

    @Test
    public void find_persistedAddressById_ReturnAddress() {
        Address address = addressService.find(ID).get();

        assertEquals(ID, address.getId());
        assertEquals(VALUE, address.getValue());
    }

    @Test
    public void find_notPersistedAddressById_ReturnEmptyOptional() {
        Optional<Address> address = addressService.find(2L);

        assertEquals(address, Optional.empty());
    }

    @Test
    public void save_notPersistedAddress_ReturnPersistedAddressWithId() {
        Address address = new Address();
        address.setValue(VALUE);
        Address persistedAddress = addressService.save(address);

        assertNull(address.getId());
        assertNotNull(persistedAddress.getId());
        assertEquals(address.getValue(), persistedAddress.getValue());
    }
}
