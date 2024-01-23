package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle Crop requests.
 */

@RestController
@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
@RequestMapping("/crops")
public class CropsController {

  private final CropService cropService;

  @Autowired
  public CropsController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Get all Crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<CropDto> allCropsDto = allCrops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(allCropsDto);
  }

  /**
   * Get a Crop by its ID.
   */

  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) throws CropNotFoundException {
    Crop crop = cropService.getCropById(id);
    CropDto cropDto = CropDto.fromEntity(crop);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * Get all Crops in a interval date from a Farm.
   */

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsToHarvestBetween(@RequestParam LocalDate start,
      @RequestParam LocalDate end)
      throws CropNotFoundException {
    List<Crop> allCrops = cropService.getCropsToHarvestDateBetween(start, end);
    List<CropDto> data = allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Join a Crop and a Fertilizer.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> joinCropAndFertilizer(
      @PathVariable long cropId, @PathVariable long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    String message = cropService.joinCropAndFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  /**
   * Get all Fertilizers from a Crop.
   */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    Crop crop = cropService.getCropById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();

    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();

    return ResponseEntity.ok(fertilizersDto);
  }
}
