package airport.reports;

import airport.airplanes.Airplane;
import airport.passengers.Passenger;
import airport.security.SecurityCheckpoint;
import airport.gates.Gate;
import airport.runways.Runway;
import java.util.*;

public class SimulationReport {
    private int totalPassengers;
    private int totalPlanes;
    private Map<String, Integer> gateUtilization;
    private Map<String, Integer> runwayUtilization;
    private Map<String, Integer> securityCheckpointUtilization;
    private List<Integer> securityWaitTimes;
    private Map<String, Integer> planeTypeCounts;
    private int peakHourPassengers;
    private int normalHourPassengers;

    public SimulationReport() {
        this.gateUtilization = new HashMap<>();
        this.runwayUtilization = new HashMap<>();
        this.securityCheckpointUtilization = new HashMap<>();
        this.securityWaitTimes = new ArrayList<>();
        this.planeTypeCounts = new HashMap<>();
        this.peakHourPassengers = 0;
        this.normalHourPassengers = 0;
    }

    public void recordGateUsage(Gate gate) {
        gateUtilization.merge(gate.getGateNumber(), 1, Integer::sum);
    }

    public void recordRunwayUsage(Runway runway) {
        runwayUtilization.merge(runway.getRunwayNumber(), 1, Integer::sum);
    }

    public void recordSecurityCheckpointUsage(SecurityCheckpoint checkpoint) {
        securityCheckpointUtilization.merge(checkpoint.getCheckpointId(), 1, Integer::sum);
    }

    public void recordSecurityWaitTime(int minutes) {
        securityWaitTimes.add(minutes);
    }

    public void recordPlaneType(String type) {
        planeTypeCounts.merge(type, 1, Integer::sum);
    }

    public void recordPassengerCount(boolean isPeakHour) {
        if (isPeakHour) {
            peakHourPassengers++;
        } else {
            normalHourPassengers++;
        }
        totalPassengers++;
    }

    public void recordPlane() {
        totalPlanes++;
    }

    // Getters for testing
    public int getTotalPassengers() {
        return totalPassengers;
    }

    public int getPeakHourPassengers() {
        return peakHourPassengers;
    }

    public int getNormalHourPassengers() {
        return normalHourPassengers;
    }

    public Map<String, Integer> getGateUtilization() {
        return gateUtilization;
    }

    public Map<String, Integer> getRunwayUtilization() {
        return runwayUtilization;
    }

    public Map<String, Integer> getSecurityCheckpointUtilization() {
        return securityCheckpointUtilization;
    }

    public double getAverageSecurityWaitTime() {
        if (securityWaitTimes.isEmpty()) {
            return 0.0;
        }
        return securityWaitTimes.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
    }

    public int getMaxSecurityWaitTime() {
        if (securityWaitTimes.isEmpty()) {
            return 0;
        }
        return Collections.max(securityWaitTimes);
    }

    public Map<String, Integer> getPlaneTypeCounts() {
        return planeTypeCounts;
    }

    public void generateReport() {
        System.out.println("\n=== Detailed Airport Simulation Report ===");
        
        // Passenger Statistics
        System.out.println("\nPassenger Statistics:");
        System.out.println("- Total Passengers: " + totalPassengers);
        System.out.println("- Peak Hour Passengers: " + peakHourPassengers);
        System.out.println("- Normal Hour Passengers: " + normalHourPassengers);
        
        // Security Statistics
        System.out.println("\nSecurity Statistics:");
        if (!securityWaitTimes.isEmpty()) {
            System.out.println("- Average Security Wait Time: " + String.format("%.1f", getAverageSecurityWaitTime()) + " minutes");
            System.out.println("- Maximum Security Wait Time: " + getMaxSecurityWaitTime() + " minutes");
        }
        
        // Resource Utilization
        System.out.println("\nResource Utilization:");
        System.out.println("Gate Utilization:");
        gateUtilization.forEach((gate, count) -> 
            System.out.println("- Gate " + gate + ": " + count + " uses"));
        
        System.out.println("\nRunway Utilization:");
        runwayUtilization.forEach((runway, count) -> 
            System.out.println("- Runway " + runway + ": " + count + " uses"));
        
        System.out.println("\nSecurity Checkpoint Utilization:");
        securityCheckpointUtilization.forEach((checkpoint, count) -> 
            System.out.println("- Checkpoint " + checkpoint + ": " + count + " passengers"));
        
        // Plane Statistics
        System.out.println("\nPlane Statistics:");
        System.out.println("- Total Planes: " + totalPlanes);
        planeTypeCounts.forEach((type, count) -> 
            System.out.println("- " + type + " Planes: " + count));
        
        // Utilization Percentages
        System.out.println("\nUtilization Percentages:");
        double gateUtilizationPercent = (double) gateUtilization.values().stream().mapToInt(Integer::intValue).sum() 
            / (gateUtilization.size() * totalPlanes) * 100;
        double runwayUtilizationPercent = (double) runwayUtilization.values().stream().mapToInt(Integer::intValue).sum() 
            / (runwayUtilization.size() * totalPlanes) * 100;
        double securityUtilizationPercent = (double) securityCheckpointUtilization.values().stream().mapToInt(Integer::intValue).sum() 
            / (securityCheckpointUtilization.size() * totalPassengers) * 100;
        
        System.out.println("- Gate Utilization: " + String.format("%.1f", gateUtilizationPercent) + "%");
        System.out.println("- Runway Utilization: " + String.format("%.1f", runwayUtilizationPercent) + "%");
        System.out.println("- Security Checkpoint Utilization: " + String.format("%.1f", securityUtilizationPercent) + "%");
    }
} 