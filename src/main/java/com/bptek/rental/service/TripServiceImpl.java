package com.bptek.rental.service;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.service.exceptions.DistanceServiceException;
import com.bptek.rental.service.exceptions.RentalException;
import com.bptek.rental.service.exceptions.TripServiceException;

/**
 * An implementation of a TripService
 * 
 * @author Banu Kones
 *
 */
public class TripServiceImpl implements TripService
{

  private DistanceService distanceService;
  private TariffService tariffService;

  @Override
  public float getDistance(Trip trip) throws TripServiceException
  {

    validateTrip(trip);

    try
    {
      float distance = distanceService.getDistance(trip.getRoute());

      if (trip.isTwoWay())
      {
        distance += distance;
      }

      return distance;
    }
    catch (DistanceServiceException e)
    {
      throw new TripServiceException("Could not calculate the distance for the trip.", e);
    }
  }

  @Override
  public float getTripCost(Trip trip) throws TripServiceException
  {

    validateTrip(trip);

    try
    {
      float totalDistance = getDistance(trip);
      float costPerKm = tariffService.getCostForVehiclePerKM(trip.getVehicle());

      costPerKm += tariffService.getAdditionalCostPerKM(trip);

      return costPerKm * totalDistance;
    }
    catch (RentalException e)
    {
      throw new TripServiceException("Could not calculate the Trip Cost", e);
    }
  }

  private void validateTrip(Trip trip) throws TripServiceException
  {
    if (trip == null)
    {
      throw new TripServiceException("A trip can not be null.", null);
    }
    else if (trip.getVehicle() == null || trip.getRoute() == null)
    {
      StringBuffer missing = new StringBuffer();
      missing.append(trip.getVehicle() == null ? "vehicle" : "");
      if (trip.getRoute() == null)
      {
        missing.append(missing.length() > 0 ? ", route" : "route");
      }

      throw new TripServiceException("Missing required properties for a trip " + missing.toString(),
          null);

    }
  }

  public void setTariffService(TariffService tariffService)
  {
    this.tariffService = tariffService;
  }

  public void setDistanceService(DistanceService distanceService)
  {
    this.distanceService = distanceService;
  }

}
