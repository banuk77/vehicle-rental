package com.bptek.rental.service;

import com.bptek.rental.dto.Trip;
import com.bptek.rental.service.exceptions.TripServiceException;

/**
 * This is the main API. It provides Cost for a given Trip and the total
 * distance that trip includes.
 * 
 * @author Banu Kones
 *
 */
public interface TripService
{
  float getDistance(Trip trip) throws TripServiceException;

  float getTripCost(Trip trip) throws TripServiceException;
}
