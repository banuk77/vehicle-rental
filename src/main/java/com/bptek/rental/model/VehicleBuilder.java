package com.bptek.rental.model;

/**
 * An Abstract VehicleBuilder class. The Concreate builder class should ensure
 * that it sets the correct vehicleType.
 * 
 * @author Banu Kones
 *
 * @param <T>
 * @param <B>
 */
public abstract class VehicleBuilder<T extends VehicleAdapter, B extends VehicleBuilder<T, B>>
{
  private String fuel;
  private int maximumPassengers;
  private boolean hasAC;

  /**
   * create a new vehicle object with require properties. Additional properties
   * can be added via the builder methods. It is the Builders responsibility to
   * set the valid vehicleType
   * 
   * @param fuel
   * @param maximumPassengers
   * @return
   */
  protected abstract T create(String fuel, int maximumPassengers);

  public B fuel(String fuel)
  {
    this.fuel = fuel;
    return (B)this;
  }

  public B maximumPassengers(int maximumPassengers)
  {
    this.maximumPassengers = maximumPassengers;
    return (B)this;
  }

  public B hasAC(boolean hasAC)
  {
    this.hasAC = hasAC;
    return (B)this;
  }

  public T build()
  {
    T vehicle = this.create(fuel, maximumPassengers);
    apply(vehicle);

    return vehicle;
  }

  protected void apply(T vehicle)
  {
    vehicle.setHasAC(hasAC);
  }

}
