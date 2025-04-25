package airport.airplanes;

public class CargoPlane implements Airplane {
    private String destination;
    private boolean maintenanceNeeded;
    private boolean needsRefuel;
    private int cargoCapacity;
    private double fuelEfficiency;

    public CargoPlane() {
        this.destination = "Unknown";
        this.maintenanceNeeded = false;
        this.needsRefuel = false;
        this.cargoCapacity = 1000;
        this.fuelEfficiency = 0.6;
    }

    @Override
    public void fly() {
        System.out.println("Cargo Plane is flying to " + destination);
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
        System.out.println("Performing maintenance on Cargo Plane");
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
        System.out.println("Refueling Cargo Plane");
        this.needsRefuel = false;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }
}