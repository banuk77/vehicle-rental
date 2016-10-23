package com.bptek.rental.model;

/**
 * A Standard vehicle interface
 * 
 * @author Banu Kones
 *
 */
public interface Vehicle
{
  VehicleType getVehicleType();

  String getFuel();

  int getMaximumPassengers();

  boolean getHasAC();

  boolean isDiesel();
}
