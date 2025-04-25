package airport.reports;

import airport.gates.Gate;
import airport.runways.Runway;
import airport.security.SecurityCheckpoint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationReportTest {
    @Test
    public void testPassengerRecording() {
        SimulationReport report = new SimulationReport();
        
        // Record passengers in peak and normal hours
        report.recordPassengerCount(true);  // Peak hour
        report.recordPassengerCount(true);  // Peak hour
        report.recordPassengerCount(false); // Normal hour
        
        // Generate report and verify counts
        report.generateReport();
        assertEquals(3, report.getTotalPassengers());
        assertEquals(2, report.getPeakHourPassengers());
        assertEquals(1, report.getNormalHourPassengers());
    }

    @Test
    public void testResourceUtilization() {
        SimulationReport report = new SimulationReport();
        
        // Record gate usage
        Gate gate1 = new Gate("G1");
        Gate gate2 = new Gate("G2");
        report.recordGateUsage(gate1);
        report.recordGateUsage(gate1);
        report.recordGateUsage(gate2);
        
        // Record runway usage
        Runway runway1 = new Runway("R1");
        report.recordRunwayUsage(runway1);
        
        // Record security checkpoint usage
        SecurityCheckpoint checkpoint1 = new SecurityCheckpoint("SC1");
        report.recordSecurityCheckpointUsage(checkpoint1);
        report.recordSecurityCheckpointUsage(checkpoint1);
        
        // Generate report and verify counts
        report.generateReport();
        assertEquals(2, report.getGateUtilization().get("G1"));
        assertEquals(1, report.getGateUtilization().get("G2"));
        assertEquals(1, report.getRunwayUtilization().get("R1"));
        assertEquals(2, report.getSecurityCheckpointUtilization().get("SC1"));
    }

    @Test
    public void testSecurityWaitTimes() {
        SimulationReport report = new SimulationReport();
        
        // Record security wait times
        report.recordSecurityWaitTime(5);
        report.recordSecurityWaitTime(10);
        report.recordSecurityWaitTime(15);
        
        // Generate report and verify average
        report.generateReport();
        assertEquals(10.0, report.getAverageSecurityWaitTime(), 0.1);
        assertEquals(15, report.getMaxSecurityWaitTime());
    }

    @Test
    public void testPlaneTypeRecording() {
        SimulationReport report = new SimulationReport();
        
        // Record different plane types
        report.recordPlaneType("Passenger");
        report.recordPlaneType("Passenger");
        report.recordPlaneType("Cargo");
        
        // Generate report and verify counts
        report.generateReport();
        assertEquals(2, report.getPlaneTypeCounts().get("Passenger"));
        assertEquals(1, report.getPlaneTypeCounts().get("Cargo"));
    }
} 