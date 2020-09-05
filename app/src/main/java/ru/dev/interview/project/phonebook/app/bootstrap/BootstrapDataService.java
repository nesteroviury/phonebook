package ru.dev.interview.project.phonebook.app.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.dev.interview.project.phonebook.core.services.api.AddressService;
import ru.dev.interview.project.phonebook.core.services.api.AddressTypeService;
import ru.dev.interview.project.phonebook.core.services.api.ContactService;
import ru.dev.interview.project.phonebook.core.services.api.ContactTypeService;
import ru.dev.interview.project.phonebook.core.services.api.PersonService;
import ru.dev.interview.project.phonebook.domain.entity.Address;
import ru.dev.interview.project.phonebook.domain.entity.AddressType;
import ru.dev.interview.project.phonebook.domain.entity.Contact;
import ru.dev.interview.project.phonebook.domain.entity.ContactType;
import ru.dev.interview.project.phonebook.domain.entity.Person;

@RequiredArgsConstructor
@Service
@Slf4j
public class BootstrapDataService implements CommandLineRunner {
    private static final String ADDRESS_TYPE = "registration";
    private static final String CONTACT_TYPE = "mobile";
    private static final int PERSON_COUNT = 10;

    private final AddressService addressService;
    private final AddressTypeService addressTypeService;
    private final ContactService contactService;
    private final ContactTypeService contactTypeService;
    private final PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Start data initializing");
        AddressType addressType = addressTypeService.save(new AddressType(ADDRESS_TYPE));
        ContactType contactType = contactTypeService.save(new ContactType(CONTACT_TYPE));
        for (int i = 0; i < PERSON_COUNT; i++) {
            init("phoneNumber" + i, contactType, "address" + i, addressType, "firstName" + i,
                    "middleName" + i, "lastName" + i);
        }
        log.debug("Data initialized");
    }

    private void init(String phoneNumber, ContactType contactType, String addressValue, AddressType addressType,
                      String firstName, String middleName, String lastName) {
        Contact contact = contactService.save(new Contact(phoneNumber, contactType));
        Address address = addressService.save(new Address(addressValue, addressType));
        personService.save(Person.builder()
                .address(address)
                .contact(contact)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build());
    }
}
