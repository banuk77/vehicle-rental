package com.bptek.rental.model;

import static com.bptek.rental.util.Utils.isEmpty;

/**
 * A Vehicle adapter class to create vehicles of different types
 * 
 * @author Banu Kones
 *
 */
public abstract class VehicleAdapter implements Vehicle
{
  private VehicleType vehicleType;
  private String fuel;
  private int maximumPassengers;
  private boolean hasAC;

  protected VehicleAdapter(VehicleType vehicleType, String fuel, int maximumPassengers)
  {
    if (vehicleType == null || isEmpty(fuel) || maximumPassengers < 1)
    {
      throw new IllegalArgumentException(
          "Following properties can not be empty [vehicleType, fuel, maximumPassangers.] ");
    }
    this.vehicleType = vehicleType;
    this.fuel = fuel;
    this.maximumPassengers = maximumPassengers;
  }

  void setVehicleType(VehicleType vehicleType)
  {
    this.vehicleType = vehicleType;
  }

  void setFuel(String fuel)
  {
    this.fuel = fuel;
  }

  void setMaximumPassengers(int maximumPassengers)
  {
    this.maximumPassengers = maximumPassengers;
  }

  void setHasAC(boolean hasAC)
  {
    this.hasAC = hasAC;
  }

  public VehicleType getVehicleType()
  {
    return vehicleType;
  }

  public String getFuel()
  {
    return fuel;
  }

  public int getMaximumPassengers()
  {
    return maximumPassengers;
  }

  public boolean getHasAC()
  {
    return hasAC;
  }

  @Override
  public boolean isDiesel()
  {
    return "diesel".equals(fuel);
  }

}
