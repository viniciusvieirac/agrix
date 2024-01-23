package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * A data transfer object (DTO) representing information about a person,
 * including the person's ID, username, password, and assigned role.
 */

public record PersonDto(Long id, String username, String password, Role role) {

  /**
   * Converts the {@code PersonDto} instance to a corresponding {@link Person}
   * entity.
   *
   * @return A new instance of {@link Person} with information from the current
   *         {@code PersonDto}.
   */

  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }

}