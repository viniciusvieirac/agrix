/**
 * Custom exception class representing a scenario where a Fertilizer entity is not found.
 */

package com.betrybe.agrix.exceptions;

/**
 * Exception thrown when a Fertilizer entity is not found in the system.
 */
public class FertilizerNotFoundException extends RuntimeException {

  /**
   * Constructs a FertilizerNotFoundException with a default error message.
   * The message indicates that the Fertilizer is not found.
   */
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!"); // Indicates "Fertilizer not found!" in Portuguese
  }
}
