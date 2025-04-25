package airport.passengers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    @Test
    public void testCheckIn() {
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        assertFalse(passenger.isCheckedIn());
        
        passenger.checkIn();
        assertTrue(passenger.isCheckedIn());
    }

    @Test
    public void testSecurityClearance() {
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        assertFalse(passenger.isSecurityCleared());
        
        passenger.clearSecurity();
        assertTrue(passenger.isSecurityCleared());
    }

    @Test
    public void testBoardingWithoutSecurity() {
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        passenger.checkIn();
        
        passenger.board();
        assertFalse(passenger.isBoarded());
    }

    @Test
    public void testBoardingWithSecurity() {
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        passenger.checkIn();
        passenger.clearSecurity();
        
        passenger.board();
        assertTrue(passenger.isBoarded());
    }
} 