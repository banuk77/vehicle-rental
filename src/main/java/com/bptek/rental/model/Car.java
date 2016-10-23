package com.bptek.rental.model;

/**
 * A Car Model
 * 
 * @author Banu Kones
 *
 */
public class Car extends VehicleAdapter
{

  protected static final class CarBuilder extends VehicleBuilder<Car, CarBuilder>
  {

    @Override
    protected Car create(String fuel, int maximumPassengers)
    {
      return new Car(fuel, maximumPassengers);
    }

  }

  private Car(String fuel, int maximumPassengers)
  {
    super(VehicleType.CAR, fuel, maximumPassengers);
  }
}
