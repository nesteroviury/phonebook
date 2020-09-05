package ru.dev.interview.project.phonebook.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Setter
@ToString(exclude = "contacts")
public class ContactType {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_TYPE_SEQ")
    @SequenceGenerator(name = "CONTACT_TYPE_SEQ", sequenceName = "CONTACT_TYPE_SEQ", allocationSize = 1)
    private Long id;
    private String value;
    @OneToMany(mappedBy = "type")
    private Set<Contact> contacts = new HashSet<>();

    public ContactType(String value) {
        this.value = value;
    }
}
