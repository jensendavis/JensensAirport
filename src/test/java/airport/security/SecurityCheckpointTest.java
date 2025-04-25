package airport.security;

import airport.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityCheckpointTest {
    @Test
    public void testProcessPassengerWhenOperational() {
        SecurityCheckpoint checkpoint = new SecurityCheckpoint("SC1");
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        
        boolean result = checkpoint.processPassenger(passenger);
        assertTrue(result);
    }

    @Test
    public void testProcessPassengerWhenNotOperational() {
        SecurityCheckpoint checkpoint = new SecurityCheckpoint("SC1");
        checkpoint.setOperational(false);
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        
        boolean result = checkpoint.processPassenger(passenger);
        assertFalse(result);
    }

    @Test
    public void testProcessingTimeRecording() {
        SecurityCheckpoint checkpoint = new SecurityCheckpoint("SC1");
        Passenger passenger = new Passenger("P1", "Test Passenger", "FL123");
        
        checkpoint.processPassenger(passenger);
        assertTrue(checkpoint.getProcessingTimeMinutes() >= 5);
    }
} 