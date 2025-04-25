package airport.passengers;

public class Passenger {
    private String id;
    private String name;
    private String flightNumber;
    private boolean checkedIn;
    private boolean securityCleared;
    private boolean boarded;

    public Passenger(String id, String name, String flightNumber) {
        this.id = id;
        this.name = name;
        this.flightNumber = flightNumber;
        this.checkedIn = false;
        this.securityCleared = false;
        this.boarded = false;
    }

    public void checkIn() {
        this.checkedIn = true;
        System.out.println("Passenger " + id + " has checked in for flight " + flightNumber);
    }

    public void clearSecurity() {
        this.securityCleared = true;
        System.out.println("Passenger " + id + " has cleared security");
    }

    public void board() {
        if (!securityCleared) {
            System.out.println("Passenger " + id + " cannot board without clearing security");
            return;
        }
        this.boarded = true;
        System.out.println("Passenger " + id + " has boarded flight " + flightNumber);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getFlightNumber() { return flightNumber; }
    public boolean isCheckedIn() { return checkedIn; }
    public boolean isSecurityCleared() { return securityCleared; }
    public boolean isBoarded() { return boarded; }
}
