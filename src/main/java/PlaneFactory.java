public class PlaneFactory {

    public Plane createPlane(String planeType) {
        if (planeType.equalsIgnoreCase("Passenger")) {
            return new PassengerPlane();
        } else if (planeType.equalsIgnoreCase("Cargo")) {
            return new CargoPlane();
        } else {
            throw new IllegalArgumentException("Unknown plane type");
        }
    }
}