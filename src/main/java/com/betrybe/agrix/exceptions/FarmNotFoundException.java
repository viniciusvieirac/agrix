package com.betrybe.agrix.exceptions;

/**
 * The type Farm not found.
 */
public class FarmNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Farm not found.
   */
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}