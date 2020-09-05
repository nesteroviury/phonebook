package ru.dev.interview.project.phonebook.app.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"ru.dev.interview.project.phonebook.core.services",
        "ru.dev.interview.project.phonebook.domain.dao",
        "ru.dev.interview.project.phonebook.domain.entity"})
@EnableJpaRepositories(basePackages = {"ru.dev.interview.project.phonebook.domain.repository"})
@EnableTransactionManagement
@EntityScan(basePackages = "ru.dev.interview.project.phonebook.domain.entity")
public class ApplicationConfiguration {
}
