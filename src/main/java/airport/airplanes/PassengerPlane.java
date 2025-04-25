package airport.airplanes;

public class PassengerPlane implements Airplane {
    private String destination;
    private boolean maintenanceNeeded;
    private boolean needsRefuel;
    private int passengerCapacity;
    private double fuelEfficiency;

    public PassengerPlane() {
        this.destination = "Unknown";
        this.maintenanceNeeded = false;
        this.needsRefuel = false;
        this.passengerCapacity = 150;
        this.fuelEfficiency = 0.8;
    }

    @Override
    public void fly() {
        System.out.println("Passenger Plane is flying to " + destination);
    }

    @Override
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceNeeded;
    }

    public void setMaintenanceNeeded(boolean maintenanceNeeded) {
        this.maintenanceNeeded = maintenanceNeeded;
    }

    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on Passenger Plane");
        this.maintenanceNeeded = false;
    }

    @Override
    public boolean needsRefueling() {
        return needsRefuel;
    }

    public void setNeedsRefuel(boolean needsRefuel) {
        this.needsRefuel = needsRefuel;
    }

    @Override
    public void refuel() {
        System.out.println("Refueling Passenger Plane");
        this.needsRefuel = false;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }
}