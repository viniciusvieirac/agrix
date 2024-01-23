package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * A data transfer object (DTO) representing essential information about a
 * person
 * without exposing sensitive password details.
 */
public record PersonDtoWithoutPassword(Long id, String username, Role role) {

  /**
   * Creates a new {@code PersonDtoWithoutPassword} instance from a {@link Person}
   * entity.
   *
   * @param person The {@link Person} entity to convert.
   * @return A new instance of {@code PersonDtoWithoutPassword} with relevant
   *         information
   *         from the provided {@link Person} entity.
   */

  public static PersonDtoWithoutPassword fromEntity(Person person) {
    return new PersonDtoWithoutPassword(
        person.getId(),
        person.getUsername(),
        person.getRole());
  }

}