package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.controller.dto.PersonDtoWithoutPassword;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing person-related operations.
 * Handles HTTP requests related to persons, such as creating new persons.
 * Exposes endpoints under the "/persons" base path.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  /**
   * Constructs a new {@code PersonController} with the specified
   * {@link PersonService}.
   *
   * @param personService The service responsible for managing person-related
   *                      operations.
   */

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Handles HTTP POST requests to create a new person.
   *
   * @param person The {@link PersonDto} containing person information to be
   *               created.
   * @return A {@link ResponseEntity} with the newly created person's information
   *         in the {@link PersonDtoWithoutPassword} format.
   */

  @PostMapping()
  public ResponseEntity<PersonDtoWithoutPassword> createPerson(@RequestBody PersonDto person) {
    Person newPerson = personService.create(person.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(PersonDtoWithoutPassword.fromEntity(newPerson));
  }

}
