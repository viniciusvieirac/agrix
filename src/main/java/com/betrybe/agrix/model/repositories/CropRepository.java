package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to handle Crops requests.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  Optional<List<Crop>> findByHarvestDateBetween(LocalDate firstDate, LocalDate lastDate);
}
