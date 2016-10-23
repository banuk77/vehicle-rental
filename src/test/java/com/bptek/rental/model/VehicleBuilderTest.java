package com.bptek.rental.model;

import org.junit.Assert;
import org.junit.Test;

import com.bptek.rental.model.Car.CarBuilder;

public class VehicleBuilderTest
{

  @SuppressWarnings("rawtypes")
  @Test
  public void vehicleBuilderFactory_Should_Create_A_Valid_VehicleBuilder()
  {
    VehicleBuilder builder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.BUS);
    checkBuilder(builder, VehicleType.BUS);
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void vehicleBuilder_Should_build_A_Valid_Vehicle()
  {
    CarBuilder builder = new Car.CarBuilder();
    checkBuilder(builder, VehicleType.CAR);

  }

  private void checkBuilder(VehicleBuilder builder, VehicleType vehicleType)
  {
    builder.fuel("petrol");
    builder.maximumPassengers(4);
    builder.hasAC(true);

    Vehicle car = builder.build();

    Assert.assertEquals("Builder doesn't satisfy the property fuel.", "petrol", car.getFuel());

    Assert.assertEquals("Builder doesn't satisfy the property maximumPassengers.", 4,
        car.getMaximumPassengers());

    Assert.assertEquals("Builder doesn't satisfy the property vehicleType.", vehicleType,
        car.getVehicleType());

    Assert.assertEquals("Builder doesn't satisfy the property hasAC.", true, car.getHasAC());

  }
}
