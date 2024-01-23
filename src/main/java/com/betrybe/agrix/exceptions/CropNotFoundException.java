package com.betrybe.agrix.exceptions;

/**
 * The type Farm not found.
 */
public class CropNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Farm not found.
   */
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}