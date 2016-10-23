package com.bptek.rental.service;

import static com.bptek.rental.util.Utils.isEmpty;

import java.util.List;

import com.bptek.rental.service.exceptions.DistanceServiceException;

/**
 * A hardcoded version of DistanceService
 * 
 * @author Banu Kones
 *
 */
public class HardCodedDistanceServiceImpl implements DistanceService
{

  // HardCoded distances. in a real world application the distances should be
  // received from database or from another service.
  private static final int CITY_PUNE = 0;
  private static final int CITY_BANGALORE = 1;
  private static final int CITY_MUMBAI = 2;
  private static final int CITY_DELI = 3;
  private static final int CITY_CHENNAI = 4;

  private static float[][] DISTANCES = { { 0, 1000, 200, 2250, 1400 }, { 1000, 0, 500, 600, 300 },
      { 200, 200, 0, 2050, 1234.5f }, { 2250, 600, 2050, 0, 3000 },
      { 1400, 300, 1234.5f, 3000, 0 }, };

  @Override
  public float getDistance(List<String> route) throws DistanceServiceException
  {
    if (route == null || route.size() < 2)
    {
      // we can not find the distance if there is no destinations in the route.
      // minimum 2
      throw new DistanceServiceException("Unknown city exception.", null);
    }

    float distance = 0;

    int startIndex = getCityIndex(route.get(0));
    int size = route.size();
    for (int i = 1; i < size; i++)
    {
      String end = route.get(i);
      int endIndex = getCityIndex(end);

      if (startIndex == -1 || endIndex == -1)
      {
        throw new DistanceServiceException("Unknown city exception.", null);
      }

      distance += DISTANCES[startIndex][endIndex];
      startIndex = endIndex;

    }
    return distance;
  }

  private int getCityIndex(String city)
  {
    if (isEmpty(city))
    {
      return -1;
    }
    city = city.toLowerCase();

    switch (city)
    {
      case "pune":
        return CITY_PUNE;
      case "bangalore":
        return CITY_BANGALORE;
      case "mumbai":
        return CITY_MUMBAI;
      case "delhi":
        return CITY_DELI;
      case "chennai":
        return CITY_CHENNAI;
    }

    return -1;
  }

}
