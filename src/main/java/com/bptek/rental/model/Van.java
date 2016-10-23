package com.bptek.rental.model;

/**
 * Van Model
 * 
 * @author Banu Kones
 *
 */
public class Van extends VehicleAdapter
{

  protected static final class VanBuilder extends VehicleBuilder<Van, VanBuilder>
  {

    @Override
    protected Van create(String fuel, int maximumPassengers)
    {
      return new Van(fuel, maximumPassengers);
    }

  }

  private Van(String fuel, int maximumPassengers)
  {
    super(VehicleType.VAN, fuel, maximumPassengers);
  }
}
