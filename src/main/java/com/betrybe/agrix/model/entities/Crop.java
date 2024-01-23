package com.betrybe.agrix.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;

/**
 * Crop entity.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @CreatedDate
  private LocalDate plantedDate;

  @CreatedDate
  private LocalDate harvestDate;

  @Column(name = "planted_area")
  private Double plantedArea;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany

  //@formatter:off
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"crop_id", "fertilizer_id"})
  )

  List<Fertilizer> fertilizers;

  /**
   * Default constructor for Crop entity.
   */
  public Crop() {
  }

  /**
   * Constructor for Crop entity with specified name, planted area, and farm.
   */
  public Crop(Long id, String name, Double plantedArea, Farm farm, LocalDate plantedDate,
      LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.farm = farm;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  /**
   * Retrieves the ID of the crop.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the ID of the crop.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Retrieves the name of the crop.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the crop.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the planted area of the crop.
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Sets the planted area of the crop.
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Retrieves the farm associated with the crop.
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Sets the farm associated with the crop.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getharvestDate() {
    return harvestDate;
  }

  public void setharvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}
