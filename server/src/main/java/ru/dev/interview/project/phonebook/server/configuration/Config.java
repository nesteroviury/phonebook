package ru.dev.interview.project.phonebook.server.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"ru.dev.interview.project.phonebook.core.service",
        "ru.dev.interview.project.phonebook.domain.dao",
        "ru.dev.interview.project.phonebook.domain.entity",
        "ru.dev.interview.project.phonebook.core.mapper",
        "ru.dev.interview.project.phonebook.rest.controller"})
@EnableJpaRepositories(basePackages = {"ru.dev.interview.project.phonebook.domain.repository"})
@EnableTransactionManagement
@EntityScan(basePackages = "ru.dev.interview.project.phonebook.domain.entity")
public class Config {
}
