package airport.security;

import airport.passengers.Passenger;
import java.util.Random;

public class SecurityCheckpoint {
    private String checkpointId;
    private boolean isOperational;
    private int processingTimeMinutes;
    private Random random;

    public SecurityCheckpoint(String checkpointId) {
        this.checkpointId = checkpointId;
        this.isOperational = true;
        this.processingTimeMinutes = 5;
        this.random = new Random();
    }

    public boolean processPassenger(Passenger passenger) {
        if (!isOperational) {
            System.out.println("Security checkpoint " + checkpointId + " is not operational");
            return false;
        }

        // Simulate random delays (20% chance of delay)
        if (random.nextDouble() < 0.2) {
            int delayMinutes = random.nextInt(10) + 1;
            System.out.println("Security delay for passenger " + passenger.getId() + 
                             " at checkpoint " + checkpointId + ": " + delayMinutes + " minutes");
            processingTimeMinutes += delayMinutes;
        }

        // Simulate random security issues (5% chance)
        if (random.nextDouble() < 0.05) {
            System.out.println("Security issue detected for passenger " + passenger.getId() + 
                             " at checkpoint " + checkpointId);
            return false;
        }

        System.out.println("Passenger " + passenger.getId() + " cleared security at checkpoint " + checkpointId);
        return true;
    }

    public void setOperational(boolean operational) {
        this.isOperational = operational;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public int getProcessingTimeMinutes() {
        return processingTimeMinutes;
    }

    public String getCheckpointId() {
        return checkpointId;
    }
} 