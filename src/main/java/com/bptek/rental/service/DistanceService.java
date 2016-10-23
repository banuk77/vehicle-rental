package com.bptek.rental.service;

import java.util.List;

import com.bptek.rental.service.exceptions.DistanceServiceException;

/**
 * DistanceService provides distance calculation for a route.
 * 
 * @author Banu Kones
 *
 */
public interface DistanceService
{
  float getDistance(List<String> route) throws DistanceServiceException;
}
