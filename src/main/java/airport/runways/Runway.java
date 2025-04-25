package airport.runways;

import airport.AirportObserver;

public class Runway implements AirportObserver {
    private String runwayNumber;
    private boolean isAvailable;

    public Runway(String runwayNumber) {
        this.runwayNumber = runwayNumber;
        this.isAvailable = true;
    }

    @Override
    public void update(String event) {
        if (event.contains("arrived")) {
            isAvailable = false;
            System.out.println("Runway " + runwayNumber + ": Occupied for arrival - " + event);
        } else if (event.contains("departed")) {
            isAvailable = true;
            System.out.println("Runway " + runwayNumber + ": Clear after departure - " + event);
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getRunwayNumber() {
        return runwayNumber;
    }
} 