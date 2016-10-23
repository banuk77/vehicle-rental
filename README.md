# Vehicle Rental Application
This is a **Hands-on design/implementation exercise** requested by a company as part of interview process.
The tasked is to design an api that fits inside a vehicle rental application and provides services to calculate the cost for a trip.

## The Problem Statement.
* A vehicle can be rented for a trip. Vehicle can be a SUV, car, van, bus, etc.
* The standard rate for a petrol vehicle for a standard trip is 15 Rs/Km. Additional 2 Rs/Km charge is applicable for AC vehicles. Diesel vehicles cost a rupee less than standard rate.
* 2% discount is applicable for bus on standard rate.
* Additional charges of 1 Rs/Km/Person are charged if number of passengers exceeds the max limit of a vehicle.
* Example distance:
  * Pune - Mumbai: 200 KM
  * Pune - Bangalore: 1000 KM
  * Mumbai - Delhi: 2050 KM
  * Mumbai - Chennai: 1234.5 KM
* In this big car rental application you are tasked to develop an API which calculates the total expense for a given trip. Do not use any property file to store the values mentioned above. Instead think in terms of injectable interfaces to provide the information assuming members of your team will be responsible to develop the same. You develop a default implementation for the same where data is hard coded. Actual implementation of those may pull data from database, file or http service.
* Example trip:    Swift, Diesel, NON AC, Pune-Mumbai-Bangalore-Pune, 3 Passengers

## Solution
The main solution to find the cost for a trip is provided via `TripService`. It requires a `Trip` object to perform the calculation.
A Trip Object consist of vehicle detail, the route, numberOfPassengers and an option for two-way.
A `Vehicle` Object can be created with a `VehicleBuilder` which can be obtained from the `VehicleBuilderFactory` or instantiating one from a concrete builder type [`Car.CarBuilder, SUV.SUVBuilder` etc].

The `TripServiceImpl` requires two services `DistanceService` and `TariffService` in-order to find the distance and cost per km. This api includes the hardcoded versions of `DistanceService` and `TariffService`.

A sample usage.    
 
```java
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

    TripService tripService = new TripServiceImpl();
    //we need to set the DistanceService and TariffService that required by TripServiceImpl.
    //in a real-world application this can be set via a bean container (like spring)
    
    ((TripServiceImpl)tripService).setDistanceService(new HardCodedDistanceServiceImpl());
    ((TripServiceImpl)tripService).setTariffService(tariffService);   
    
    float cost = tripService.getTripCost(new HardCodedTariffServiceImpl());
    
```    

## Assumption
* When number of passengers exceeded the vehicle maximum, an additional fee of 1.Rs/KM/Ps is charged. Although it wasn't clear that whether the additional charge is to be applied for total number of passenger or just for the exceeded amount. This method assumes that the charge will be applied only to the exceeded number of passengers.
* A trip can be either one-way or two-way.