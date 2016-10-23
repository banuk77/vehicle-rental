package com.bptek.rental.dto;

import java.util.List;

import com.bptek.rental.model.Vehicle;

/**
 * A DTO object that holds a trip information. It is assumed that a trip can be
 * either one-way or two-way. A two way trip takes the route in reverse meaning
 * the distance will be twice the one-way trip.
 * 
 * @author Banu Kones
 *
 */
public class Trip
{
  private Vehicle vehicle;
  private List<String> route;
  private int numberOfPassengers;
  private boolean twoWay;

  public Vehicle getVehicle()
  {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

  public List<String> getRoute()
  {
    return route;
  }

  public void setRoute(List<String> route)
  {
    this.route = route;
  }

  public int getNumberOfPassengers()
  {
    return numberOfPassengers;
  }

  public void setNumberOfPassengers(int numberOfPassengers)
  {
    this.numberOfPassengers = numberOfPassengers;
  }

  public void setTwoWay(boolean twoWay)
  {
    this.twoWay = twoWay;
  }

  public boolean isTwoWay()
  {
    return twoWay;
  }
}
