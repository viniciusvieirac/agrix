package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.AuthDto;
import com.betrybe.agrix.controller.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling authentication-related requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Constructor to inject dependencies: AuthenticationManager and TokenService.
   *
   * @param authenticationManager The authentication manager to authenticate user
   *                              credentials.
   * @param tokenService          The service responsible for generating
   *                              authentication tokens.
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Endpoint for user login, which authenticates the user and returns an
   * authentication token.
   *
   * @param authDto The data transfer object (DTO) containing user credentials.
   * @return A TokenDto containing the authentication token.
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    // Creating an authentication token with the provided username and password.
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authDto.username(),
        authDto.password());

    // Authenticating the user with the authentication manager.
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    // Extracting the authenticated person details.
    Person person = (Person) auth.getPrincipal();

    // Generating an authentication token using the TokenService.
    String token = tokenService.generateToken(person);

    // Returning the authentication token in the form of a TokenDto.
    return new TokenDto(token);
  }
}
