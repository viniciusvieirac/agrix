package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropsService.
 */
@Service
public class CropService {

  private final CropRepository cropsRepository;
  private final FertilizerService fertilizerService;
  private final FarmService farmService;

  /**
   * Constructor for CropService that injects the CropRepository dependency.
   *
   * @param cropsRepository The repository handling operations related to Crop
   *                        entities.
   */

  @Autowired
  public CropService(CropRepository cropsRepository, FarmService farmService,
      FertilizerService fertilizerService) {
    this.cropsRepository = cropsRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create a crop.
   */
  public Crop createCrops(Long farmId, Crop crop) throws FarmNotFoundException {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    Farm farm = optionalFarm.orElseThrow(FarmNotFoundException::new);

    crop.setFarm(farm);
    return cropsRepository.save(crop);
  }

  public List<Crop> getCropByFarmId(Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId).orElseThrow(FarmNotFoundException::new);
    return farm.getCrops();
  }

  public List<Crop> getAllCrops() {
    return cropsRepository.findAll();
  }

  public Crop getCropById(Long id) throws CropNotFoundException {
    return cropsRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> getCropsToHarvestDateBetween(LocalDate firsDate, LocalDate lastDate)
      throws CropNotFoundException {
    return cropsRepository.findByHarvestDateBetween(firsDate, lastDate)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Join a crop with a fertilizer.
   */

  public String joinCropAndFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Optional<Crop> cropOptional = cropsRepository.findById(cropId);
    Optional<Fertilizer> fertilizerOptional = fertilizerService.findById(fertilizerId);

    if (cropOptional.isPresent() && fertilizerOptional.isPresent()) {
      Crop crop = cropOptional.get();
      Fertilizer fertilizer = fertilizerOptional.get();

      crop.getFertilizers().add(fertilizer);
      cropsRepository.save(crop);

      return "Fertilizante e plantação associados com sucesso!";
    }

    if (!cropOptional.isPresent()) {
      throw new CropNotFoundException();
    }

    throw new FertilizerNotFoundException();
  }

}
