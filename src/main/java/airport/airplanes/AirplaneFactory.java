package airport.airplanes;

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