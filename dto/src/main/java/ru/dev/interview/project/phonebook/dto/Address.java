package ru.dev.interview.project.phonebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@ToString
@Setter
public class Address implements Serializable {
    private static final long serialVersionUID = -2590520979181889334L;
    private Long id;
    private String value;
}
