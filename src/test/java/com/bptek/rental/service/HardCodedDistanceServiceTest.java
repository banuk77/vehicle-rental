package com.bptek.rental.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bptek.rental.service.exceptions.DistanceServiceException;

public class HardCodedDistanceServiceTest
{
  DistanceService distanceService;

  @Before
  public void setup()
  {
    distanceService = new HardCodedDistanceServiceImpl();
  }

  @Test
  public void getDistance_Should_Return_Valid_Value() throws DistanceServiceException
  {
    List<String> route = new ArrayList<>();
    route.add("chennai");
    route.add("mumbai");
    route.add("pune");

    float distance = distanceService.getDistance(route);

    Assert.assertTrue("Distance should be greater than 0", distance > 0);

  }

  @Test(expected = DistanceServiceException.class)
  public void getDistance_Should_Throw_RentalException() throws DistanceServiceException
  {
    List<String> route = new ArrayList<>();

    distanceService.getDistance(route);

  }
}
