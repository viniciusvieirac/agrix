package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;

/**
 * DTO to represent a Crop.
 */
public record CropDto(Long id, String name, Double plantedArea, Long farmId, LocalDate plantedDate,
    LocalDate harvestDate) {

  /**
   * Converts a Crop entity to a CropDto.
   *
   * @param crop The Crop entity to convert
   * @return CropDto representing the Crop entity
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getharvestDate());
  }

  /**
   * Converts a CropDto to a Crop entity.
   *
   * @return Crop entity representing the CropDto
   */
  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setharvestDate(harvestDate);
    return crop;
  }
}
