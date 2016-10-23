package com.bptek.rental.model;

/**
 * A Bus Model
 * 
 * @author Banu Kones
 *
 */
public class Bus extends VehicleAdapter
{

  protected static final class BusBuilder extends VehicleBuilder<Bus, BusBuilder>
  {

    @Override
    protected Bus create(String fuel, int maximumPassengers)
    {
      return new Bus(fuel, maximumPassengers);
    }

  }

  private Bus(String fuel, int maximumPassengers)
  {
    super(VehicleType.BUS, fuel, maximumPassengers);
  }
}
