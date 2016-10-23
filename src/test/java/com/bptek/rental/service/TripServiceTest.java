package com.bptek.rental.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.model.Vehicle;
import com.bptek.rental.model.VehicleBuilder;
import com.bptek.rental.model.VehicleBuilderFactory;
import com.bptek.rental.model.VehicleType;
import com.bptek.rental.service.exceptions.RentalException;
import com.bptek.rental.service.exceptions.TripServiceException;

public class TripServiceTest
{
  private TripServiceImpl tripService;

  private DistanceService distanceService;
  private TariffService tariffService;

  @Before
  public void setup()
  {
    tripService = new TripServiceImpl();

    // inject dependencies
    distanceService = new HardCodedDistanceServiceImpl();
    tariffService = new HardCodedTariffServiceImpl();

    tripService.setDistanceService(distanceService);
    tripService.setTariffService(tariffService);
  }

  @Test
  public void getTripCost_Should_Return_Valid_Cost() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.SUV);
    vehicleBuilder.fuel("diesel");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(false);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("pune");
    route.add("mumbai");
    route.add("chennai");
    route.add("pune");
    trip.setRoute(route);

    trip.setNumberOfPassengers(3);

    float cost = tripService.getTripCost(trip);

    // Again, we should mock these service so that we can set an expected cost.
    float costPerKm = tariffService.getCostForVehiclePerKM(vehicle)
        + tariffService.getAdditionalCostPerKM(trip);
    float distance = distanceService.getDistance(route); // 2834.5km
    float expectedCost = costPerKm * distance;

    Assert.assertTrue("getTripCost should return a vaid trip cost", (expectedCost == cost));

  }

  @Test
  public void getTripCost_Should_Return_Valid_TwoWay_Trip_Cost() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.SUV);
    vehicleBuilder.fuel("diesel");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(false);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("pune");
    route.add("mumbai");
    route.add("chennai");
    trip.setRoute(route);

    trip.setNumberOfPassengers(4);
    trip.setTwoWay(true);

    float cost = tripService.getTripCost(trip);

    // Again, we should mock these service so that we can set an expected cost.
    float costPerKm = tariffService.getCostForVehiclePerKM(vehicle)
        + tariffService.getAdditionalCostPerKM(trip);
    // this trip is two way.
    float distance = distanceService.getDistance(route) * 2;
    float expectedCost = costPerKm * distance;

    Assert.assertTrue("getTripCost should return a vaid trip cost", (expectedCost == cost));

  }

  @Test(expected = TripServiceException.class)
  public void getTripCost_Should_Throw_TripServiceException() throws TripServiceException
  {
    Trip trip = new Trip();
    tripService.getTripCost(trip);
  }

  @Test
  public void getTripCost_Should_Return_Valid_Cost_For_Bus() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.BUS);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(14);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("chennai");
    route.add("mumbai");
    route.add("pune");
    trip.setRoute(route);

    trip.setNumberOfPassengers(14);

    float cost = tripService.getTripCost(trip);

    // Again, we should mock these service so that we can set an expected cost.
    float costPerKm = tariffService.getCostForVehiclePerKM(vehicle)
        + tariffService.getAdditionalCostPerKM(trip);
    float distance = distanceService.getDistance(route);
    float expectedCost = costPerKm * distance;

    Assert.assertTrue("getTripCost should return a vaid trip cost", (expectedCost == cost));

  }

  @Test
  public void getTripCost_Should_Return_Valid_Cost_For_Excess_Passengers() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.BUS);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(14);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("chennai");
    route.add("mumbai");
    route.add("pune");
    trip.setRoute(route);

    trip.setNumberOfPassengers(17);

    float cost = tripService.getTripCost(trip);

    // Again, we should mock these service so that we can set an expected cost.
    float costPerKm = tariffService.getCostForVehiclePerKM(vehicle)
        + tariffService.getAdditionalCostPerKM(trip);
    float distance = distanceService.getDistance(route);
    float expectedCost = costPerKm * distance;

    Assert.assertTrue("getTripCost should return a vaid trip cost", (expectedCost == cost));

  }

  @Test
  public void getDistance_Should_Return_Valid_Distance() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.CAR);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("chennai");
    route.add("mumbai");
    route.add("pune");
    trip.setRoute(route);

    trip.setNumberOfPassengers(4);

    float distance = tripService.getDistance(trip);

    // It would be nice if we could Mock this service.
    float expectedDistance = distanceService.getDistance(route);
    // 1434.5

    Assert.assertTrue("Trip service should return a vaid total trip distance.",
        (expectedDistance == distance));
  }

  @Test
  public void getDistance_Should_Return_Valid_Distance_For_TwoWay_Trip() throws RentalException
  {
    Trip trip = new Trip();

    VehicleBuilder vehicleBuilder = VehicleBuilderFactory.getVehicleBuilder(VehicleType.CAR);
    vehicleBuilder.fuel("petrol");
    vehicleBuilder.maximumPassengers(4);
    vehicleBuilder.hasAC(true);
    Vehicle vehicle = vehicleBuilder.build();
    trip.setVehicle(vehicle);

    List<String> route = new ArrayList<>();
    route.add("chennai");
    route.add("mumbai");
    route.add("pune");
    trip.setRoute(route);

    trip.setNumberOfPassengers(4);
    trip.setTwoWay(true);

    float distance = tripService.getDistance(trip);

    // It would be nice if we could Mock this service.
    float expectedDistance = distanceService.getDistance(route) * 2;
    // 2869

    Assert.assertTrue("Trip service should return a vaid total trip distance.",
        (expectedDistance == distance));
  }

  @Test(expected = TripServiceException.class)
  public void getDistance_Should_Throw_TripServiceException() throws TripServiceException
  {
    Trip trip = new Trip();
    tripService.getDistance(trip);
  }

}
