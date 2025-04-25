package airport.airplanes;

public interface Airplane {
    void fly();
    String getDestination();
    boolean needsMaintenance();
    void performMaintenance();
    boolean needsRefueling();
    void refuel();
}