package com.bptek.rental.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.model.Vehicle;
import com.bptek.rental.model.VehicleBuilder;
import com.bptek.rental.model.VehicleBuilderFactory;
import com.bptek.rental.model.VehicleType;
import com.bptek.rental.service.exceptions.TariffServiceException;

public class HardCodedTariffServiceTest
{
  private HardCodedTariffServiceImpl tariffService;

  private float standardRate = 15f;

  @Before
  public void setup()
  {
    tariffService = new HardCodedTariffServiceImpl();
    tariffService.setStandardRate(standardRate);
  }

  @Test
  public void getCostForVehiclePerKM_Should_Return_Valid_Cost_For_Standard()
      throws TariffServiceException
  {
    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.CAR);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(false);
    Vehicle vehicle = vehicleBuilder.build();

    float cost = tariffService.getCostForVehiclePerKM(vehicle);

    float expected = standardRate;

    Assert.assertTrue("Cost for standard trip should be " + expected, expected == cost);

  }

  @Test(expected = TariffServiceException.class)
  public void getCostForVehiclePerKM_Should_Throw_TariffServiceException()
      throws TariffServiceException
  {

    float cost = tariffService.getCostForVehiclePerKM(null);

    float expected = standardRate;

    Assert.assertTrue("Cost for standard trip should be " + expected, expected == cost);

  }

  @Test
  public void getCostForVehiclePerKM_Should_Return_Valid_Cost_For_Bus()
      throws TariffServiceException
  {
    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.BUS);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(false);
    Vehicle vehicle = vehicleBuilder.build();

    float cost = tariffService.getCostForVehiclePerKM(vehicle);

    float expected = standardRate * .98f;

    Assert.assertTrue("Cost for standard trip should be " + expected, expected == cost);

  }

  @Test
  public void getCostForVehiclePerKM_Should_Return_Valid_Cost_For_AC_BUS_Trip()
      throws TariffServiceException
  {
    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.BUS);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(14);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();

    float cost = tariffService.getCostForVehiclePerKM(vehicle);

    float expected = standardRate * .98f + 2;

    Assert.assertTrue("Cost for AC Bus trip should be " + expected, expected == cost);

  }

  @Test
  public void getCostForVehiclePerKM_Should_Return_Valid_Cost_For_AC_Diesel_Van_Trip()
      throws TariffServiceException
  {
    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.VAN);
    vehicleBuilder.fuel("diesel");
    vehicleBuilder.maximumPassengers(9);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();

    float cost = tariffService.getCostForVehiclePerKM(vehicle);

    float expected = standardRate + 2 - 1;

    Assert.assertTrue("Cost for AC Diesel Van trip should be " + expected, expected == cost);

  }

  public void getAdditionalCostPerKM_Should_Return_Valid_Cost_For_AC_Diesel_Van_With_Excess_Passenger()
      throws TariffServiceException
  {
    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.VAN);
    vehicleBuilder.fuel("diesel");
    vehicleBuilder.maximumPassengers(9);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();

    Trip trip = new Trip();
    trip.setVehicle(vehicle);
    trip.setNumberOfPassengers(12);

    float cost = tariffService.getAdditionalCostPerKM(trip);

    float expected = standardRate + 2 - 1 + 3;

    Assert.assertTrue("Cost for AC Diesel Van with Excess Passenger trip should be " + expected,
        expected == cost);

  }

}
