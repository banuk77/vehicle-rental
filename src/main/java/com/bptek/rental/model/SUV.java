package com.bptek.rental.model;

/**
 * SUV Model
 * 
 * @author Banu Kones
 *
 */
public class SUV extends VehicleAdapter
{

  protected static final class SUVBuilder extends VehicleBuilder<SUV, SUVBuilder>
  {

    @Override
    protected SUV create(String fuel, int maximumPassengers)
    {
      return new SUV(fuel, maximumPassengers);
    }

  }

  private SUV(String fuel, int maximumPassengers)
  {
    super(VehicleType.SUV, fuel, maximumPassengers);
  }
}
