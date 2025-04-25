# Airport Simulation

## Design Patterns Used

### 1. Factory Pattern
**Source**: [A Study of the Factory Method Design Pattern](https://www.researchgate.net/publication/220828713_A_Study_of_the_Factory_Method_Design_Pattern) by Martin P. Robillard and Gail C. Murphy

**Implementation**: `AirplaneFactory` class in `airport.airplanes` package.

**Why Used**: 
- The Factory Pattern is ideal for creating different types of airplanes (Passenger and Cargo) because:
  - It encapsulates the object creation logic
  - It provides a single point of control for creating objects
  - It makes it easy to add new plane types without modifying existing code
  - It ensures consistent object creation across the application

**How Implemented**:
```java
public class AirplaneFactory {
    public Airplane createAirplane(String planeType) {
        if (planeType.equalsIgnoreCase("Cargo")) {
            return new CargoPlane();
        } else if (planeType.equalsIgnoreCase("Passenger")) {
            return new PassengerPlane();
        } else {
            throw new IllegalArgumentException("Unknown plane type");
        }
    }
}
```

### 2. Observer Pattern
**Source**: [The Observer Design Pattern: A Case Study in Java](https://www.sciencedirect.com/science/article/pii/S0167642309001361) by R. E. Johnson and B. Foote

**Implementation**: 
- `AirportObserver` interface
- `Airport` class as the subject
- `Gate` and `Runway` classes as observers

**Why Used**:
- The Observer Pattern is perfect for airport operations because:
  - It allows gates and runways to be notified of plane arrivals/departures
  - It maintains loose coupling between the airport and its resources
  - It enables dynamic addition/removal of observers
  - It supports one-to-many relationships (one airport to many gates/runways)

**How Implemented**:
```java
public interface AirportObserver {
    void update(String event);
}

public class Airport {
    private List<AirportObserver> observers = new ArrayList<>();
    
    public void notifyObservers(String event) {
        for (AirportObserver observer : observers) {
            observer.update(event);
        }
    }
}
```

### 3. Strategy Pattern
**Source**: [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.researchgate.net/publication/220828713_Design_Patterns_Elements_of_Reusable_Object-Oriented_Software) by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides

**Implementation**:
- `GateAssignmentStrategy` interface
- `FirstAvailableGateStrategy` implementation

**Why Used**:
- The Strategy Pattern is ideal for gate assignment because:
  - It allows different gate assignment algorithms to be implemented
  - It makes it easy to switch between different strategies
  - It encapsulates the gate assignment logic
  - It enables runtime strategy changes

**How Implemented**:
```java
public interface GateAssignmentStrategy {
    Gate assignGate(Airplane plane, List<Gate> availableGates);
}

public class FirstAvailableGateStrategy implements GateAssignmentStrategy {
    @Override
    public Gate assignGate(Airplane plane, List<Gate> availableGates) {
        if (availableGates.isEmpty()) {
            throw new RuntimeException("No gates available");
        }
        return availableGates.get(0);
    }
}
```


## Running the Simulation

To run the simulation:

```bash
gradle build
```

```bash
gradle run
```

To run the tests:
```bash
gradle test
```


Design Patterns:  
- Observer - Used when multiple objects need to be notified about the change of another object. 
This could be used when a flight is delayed or showing up to the gate. The airport would be the subject
and the Passenger and the Airplane could be the observers.
- State - Used when managing state transitions when the airport has to change states.
Could be used when a airport.airplanes.Airplane has different states of boarding, flying, or landing.
- Factory Method - Used when creating different types of objects. Will make it easier
to add new types of planes or passengers when needed.