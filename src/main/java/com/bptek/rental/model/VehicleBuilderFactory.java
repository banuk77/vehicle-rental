package com.bptek.rental.model;

/**
 * A factory to create various VehicleBuilders
 * 
 * @author Banu Kones
 *
 */
public class VehicleBuilderFactory
{

  public static final VehicleBuilder<?, ?> getVehicleBuilder(VehicleType type)
  {
    VehicleBuilder<?, ?> builder = null;

    switch (type)
    {
      case SUV:
        builder = new SUV.SUVBuilder();
        break;
      case CAR:
        builder = new Car.CarBuilder();
        break;
      case VAN:
        builder = new Van.VanBuilder();
        break;
      case BUS:
        builder = new Bus.BusBuilder();
        break;

      default:
        return null;
    }

    return builder;

  }
}
