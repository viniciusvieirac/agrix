/**
 * Entity class representing Fertilizer information.
 */

package com.betrybe.agrix.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entity representing information about a Fertilizer used in agricultural
 * activities.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private String composition;

  @ManyToMany(mappedBy = "fertilizers")
  List<Crop> crops;

  /**
   * Default constructor for the Fertilizer entity.
   */
  public Fertilizer() {
  }

  /**
   * Parameterized constructor for the Fertilizer entity.
   *
   * @param name        The name of the fertilizer.
   * @param brand       The brand of the fertilizer.
   * @param composition The composition details of the fertilizer.
   */
  public Fertilizer(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  /**
   * Get the ID of the fertilizer.
   *
   * @return The ID of the fertilizer.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the ID of the fertilizer.
   *
   * @param id The ID of the fertilizer.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the fertilizer.
   *
   * @return The name of the fertilizer.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the fertilizer.
   *
   * @param name The name of the fertilizer.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the brand of the fertilizer.
   *
   * @return The brand of the fertilizer.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Set the brand of the fertilizer.
   *
   * @param brand The brand of the fertilizer.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Get the composition details of the fertilizer.
   *
   * @return The composition details of the fertilizer.
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Set the composition details of the fertilizer.
   *
   * @param composition The composition details of the fertilizer.
   */
  public void setComposition(String composition) {
    this.composition = composition;
  }
}
