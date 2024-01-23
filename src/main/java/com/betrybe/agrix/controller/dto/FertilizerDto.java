/**
 * A data transfer object (DTO) representing Fertilizer information for API communication.
 */

package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Fertilizer;

/**
 * A record representing a Fertilizer data transfer object (DTO) containing
 * basic fertilizer information.
 *
 * @param id          The unique identifier of the fertilizer.
 * @param name        The name of the fertilizer.
 * @param brand       The brand of the fertilizer.
 * @param composition The composition details of the fertilizer.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition) {

  /**
   * Constructs a FertilizerDto instance from a given Fertilizer entity.
   *
   * @param fertilizer The Fertilizer entity to convert into a DTO.
   * @return A FertilizerDto instance representing the provided Fertilizer entity.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition());
  }

  /**
   * Converts the FertilizerDto instance into a Fertilizer entity.
   *
   * @return A Fertilizer entity populated with the data from the DTO.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(name);
    fertilizer.setBrand(brand);
    fertilizer.setComposition(composition);
    return fertilizer;
  }
}
