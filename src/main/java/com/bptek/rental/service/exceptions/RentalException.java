package com.bptek.rental.service.exceptions;

/**
 * The Root Exception for the Rental application
 * 
 * @author Banu Kones
 *
 */
public class RentalException extends Exception
{
  public RentalException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
