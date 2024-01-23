package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
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
 * Controller to handle Farm requests.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {


  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  public ResponseEntity<FarmDto> insertFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(newFarm));
  }

  /**
   * Get all Farms.
   */
  
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    List<FarmDto> allFarmsDto = allFarms.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.ok(allFarmsDto);
  }

  /**
   * Get a Farm by its ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    Optional<Farm> farmById = farmService.getFarmById(id);
    return ResponseEntity.ok(FarmDto.fromEntity(farmById.orElseThrow(FarmNotFoundException::new)));
  }

  @PostMapping("/{id}/crops")
  public ResponseEntity<?> insertCrop(@PathVariable Long id, @RequestBody CropDto cropDto)
      throws FarmNotFoundException {
    CropDto newCrop = CropDto.fromEntity(cropService.createCrops(id, cropDto.toEntity()));
    return ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }

  /**
   * Get all crops from a farm.
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<?> getCropsByFarmId(@PathVariable Long id) throws FarmNotFoundException {
    List<Crop> cropsByFarmId = cropService.getCropByFarmId(id);
    List<CropDto> cropsDto = cropsByFarmId.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsDto);
  }
}
