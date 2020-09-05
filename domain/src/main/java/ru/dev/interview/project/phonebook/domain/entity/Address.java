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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Setter
@ToString(exclude = "person")
public class Address {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
    private Long id;
    private String value;
    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private AddressType type;
    @OneToOne(mappedBy = "address")
    private Person person;

    public Address(String value, AddressType type) {
        this.value = value;
        this.type = type;
    }
}
