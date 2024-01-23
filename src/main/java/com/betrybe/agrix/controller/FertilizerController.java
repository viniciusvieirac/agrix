/**
 * Controller handling HTTP requests related to Fertilizer entities.
 */

package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Fertilizer entities.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Constructor for FertilizerController that injects the FertilizerService
   * dependency.
   *
   * @param fertilizerService The service handling operations related to
   *                          Fertilizer entities.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Endpoint to create a new Fertilizer using a FertilizerDto.
   *
   * @param fertilizer The DTO containing the details of the new Fertilizer to be
   *                   created.
   * @return ResponseEntity with the created FertilizerDto and HTTP status 201
   *         (Created) upon success.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> create(@RequestBody FertilizerDto fertilizer) {
    Fertilizer createdFertilizer = fertilizerService.create(fertilizer.toEntity());
    return ResponseEntity
        .status(HttpStatus.CREATED).body(FertilizerDto.fromEntity(createdFertilizer));
  }

  /**
   * Endpoint to retrieve all Fertilizers.
   *
   * @return ResponseEntity with a list of all FertilizerDto and HTTP status 200
   *         (OK) upon success.
   */

  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getMethodName() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    List<FertilizerDto> allFertilizersDto = fertilizers
        .stream().map(FertilizerDto::fromEntity).toList();
    return ResponseEntity.ok(allFertilizersDto);
  }

  /**
   * Retrieves a Fertilizer by its ID.
   *
   * @param id The unique identifier of the Fertilizer.
   * @return A ResponseEntity containing either the found FertilizerDto if present
   *         or a ResponseEntity
   *         with an error status indicating that the Fertilizer was not found.
   * @throws FertilizerNotFoundException If the requested Fertilizer with the
   *                                     specified ID is not found.
   */

  @GetMapping("/{id}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    Optional<Fertilizer> fertilizerById = fertilizerService.findById(id);
    return ResponseEntity.ok(FertilizerDto
        .fromEntity(fertilizerById.orElseThrow(FertilizerNotFoundException::new)));
  }

}
