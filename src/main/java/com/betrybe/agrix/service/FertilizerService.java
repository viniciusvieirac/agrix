/**
 * Service class for handling operations related to Fertilizer entities.
 */

package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Fertilizer entities.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor for FertilizerService that injects the FertilizerRepository
   * dependency.
   *
   * @param fertilizerRepository The repository for Fertilizer entities.
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Creates a new Fertilizer entity by saving it using the repository.
   *
   * @param fertilizer The Fertilizer object to be created.
   * @return The created Fertilizer object.
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> findById(Long id) {
    return fertilizerRepository.findById(id);
  }
}
