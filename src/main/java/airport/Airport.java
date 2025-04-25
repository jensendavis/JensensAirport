package airport;

import airport.airplanes.*;
import airport.gates.Gate;
import airport.gates.GateAssignmentStrategy;
import airport.runways.Runway;
import airport.security.SecurityCheckpoint;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<AirportObserver> observers = new ArrayList<>();
    private List<Gate> gates = new ArrayList<>();
    private List<Runway> runways = new ArrayList<>();
    private List<SecurityCheckpoint> securityCheckpoints = new ArrayList<>();
    private AirplaneFactory airplaneFactory;
    private GateAssignmentStrategy gateStrategy;

    public Airport(GateAssignmentStrategy strategy) {
        this.airplaneFactory = new AirplaneFactory();
        this.gateStrategy = strategy;
        initializeAirport();
    }

    private void initializeAirport() {
        // Initialize gates
        for (int i = 1; i <= 3; i++) {
            Gate gate = new Gate("G" + i);
            gates.add(gate);
            addObserver(gate);
        }

        // Initialize runways
        for (int i = 1; i <= 2; i++) {
            Runway runway = new Runway("R" + i);
            runways.add(runway);
            addObserver(runway);
        }

        // Initialize security checkpoints
        for (int i = 1; i <= 2; i++) {
            SecurityCheckpoint checkpoint = new SecurityCheckpoint("SC" + i);
            securityCheckpoints.add(checkpoint);
        }
    }

    public void addObserver(AirportObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AirportObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (AirportObserver observer : observers) {
            observer.update(event);
        }
    }

    public Airplane processPlaneArrival(String planeType) {
        // Create plane using factory pattern
        Airplane plane = airplaneFactory.createAirplane(planeType);
        
        // Assign gate using strategy pattern
        Gate assignedGate = gateStrategy.assignGate(plane, gates);
        
        // Notify observers (Observer pattern)
        String event = planeType + " plane has arrived and assigned to gate " + assignedGate.getGateNumber();
        System.out.println(event);
        notifyObservers(event);
        
        // Simulate plane movement
        plane.fly();
        
        return plane;
    }

    public void processPlaneDeparture(String planeType) {
        String event = planeType + " plane has departed";
        System.out.println(event);
        notifyObservers(event);
    }

    public List<SecurityCheckpoint> getSecurityCheckpoints() {
        return securityCheckpoints;
    }

    public SecurityCheckpoint getAvailableSecurityCheckpoint() {
        for (SecurityCheckpoint checkpoint : securityCheckpoints) {
            if (checkpoint.isOperational()) {
                return checkpoint;
            }
        }
        return null;
    }
} 