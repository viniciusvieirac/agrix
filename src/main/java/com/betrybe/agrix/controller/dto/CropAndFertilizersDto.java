package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Crop And fertilizers dto.
 */
public record CropAndFertilizersDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId,
    List<FertilizerDto> fertilizers) {

  /**
   * From entity crop And fertilizers dto.
   *
   * @param crop the crop
   * @return the crop And fertilizers dto
   */
  public static CropAndFertilizersDto fromEntity(Crop crop) {
    return new CropAndFertilizersDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getharvestDate(),
        crop.getFarm().getId(),
        crop.getFertilizers().stream()
            .map(FertilizerDto::fromEntity)
            .toList());
  }

}