package com.bptek.rental.service;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.model.Vehicle;
import com.bptek.rental.service.exceptions.TariffServiceException;

/**
 * TariffService provides Cost for a vehicle per KM and any additional cost that
 * should be charded for a trip.
 * 
 * @author Banu Kones
 *
 */
public interface TariffService
{
  float getCostForVehiclePerKM(Vehicle vehicle) throws TariffServiceException;

  float getAdditionalCostPerKM(Trip trip) throws TariffServiceException;

}
