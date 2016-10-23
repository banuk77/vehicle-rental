package com.bptek.rental.service;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.model.Vehicle;
import com.bptek.rental.model.VehicleType;
import com.bptek.rental.service.exceptions.TariffServiceException;

/**
 * A hardcoded version of TariffService
 * 
 * @author Banu Kones
 *
 */
public class HardCodedTariffServiceImpl implements TariffService
{

  private float standardRate = 15f;

  public void setStandardRate(float standardRate)
  {
    this.standardRate = standardRate;
  }

  public float getStandardCostPerKM()
  {
    return standardRate;
  }

  public float getCostForBus()
  {
    return getStandardCostPerKM() * .98f;
  }

  @Override
  public float getCostForVehiclePerKM(Vehicle vehicle) throws TariffServiceException
  {
    checkVehicle(vehicle);

    float cost = vehicle.getVehicleType() == VehicleType.BUS ? getCostForBus()
        : getStandardCostPerKM();

    if (vehicle.getHasAC())
    {
      cost += 2;
    }

    if (vehicle.isDiesel())
    {
      cost -= 1;
    }

    return cost;
  }

  // When number of passengers exceeded the vehicle maximum, an additional fee
  // of 1.Rs/KM/Ps is charged.
  // Although it wasn't clear that whether the additional charge is to be
  // applied for total number of passenger or just for the exceeded amount.
  // This method assumes that the charge will be applied only to the exceeded
  // number of passengers.
  @Override
  public float getAdditionalCostPerKM(Trip trip) throws TariffServiceException
  {
    int passengers = trip.getNumberOfPassengers();
    Vehicle vehicle = trip.getVehicle();

    checkVehicle(vehicle);

    if (passengers > vehicle.getMaximumPassengers())
    {
      return (passengers - vehicle.getMaximumPassengers());
    }

    return 0;
  }

  private void checkVehicle(Vehicle vehicle) throws TariffServiceException
  {
    if (vehicle == null)
    {
      throw new TariffServiceException("Required property 'vehicle' can not be null.", null);
    }
  }

}
