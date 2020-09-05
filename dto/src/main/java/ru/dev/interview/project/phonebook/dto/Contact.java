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
public class Contact implements Serializable {
    private Long id;
    private String value;
}
