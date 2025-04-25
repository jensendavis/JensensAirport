package airport;

import airport.gates.FirstAvailableGateStrategy;
import airport.airplanes.Airplane;
import airport.airplanes.PassengerPlane;
import airport.airplanes.CargoPlane;
import airport.passengers.Passenger;
import airport.security.SecurityCheckpoint;
import airport.reports.SimulationReport;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class AirportSimulation {
    private static final int SIMULATION_CYCLES = 10;
    private static final int CYCLE_DURATION_MINUTES = 30;
    private static final Random random = new Random();
    private static final String[] PLANE_TYPES = {"Passenger", "Cargo"};
    private static final String[] FLIGHT_NUMBERS = {"AA100", "BA200", "CA300", "DA400", "EA500"};
    private static final String[] DESTINATIONS = {"New York", "London", "Tokyo", "Paris", "Sydney"};

    public static void main(String[] args) {
        // Initialize airport with first available gate strategy
        Airport airport = new Airport(new FirstAvailableGateStrategy());
        List<Passenger> passengers = new ArrayList<>();
        List<Airplane> planes = new ArrayList<>();
        SimulationReport report = new SimulationReport();

        System.out.println("=== Starting Airport Simulation ===\n");
        System.out.println("Simulation will run for " + SIMULATION_CYCLES + " cycles");
        System.out.println("Each cycle represents " + CYCLE_DURATION_MINUTES + " minutes\n");

        // Run simulation cycles
        for (int cycle = 1; cycle <= SIMULATION_CYCLES; cycle++) {
            System.out.println("--- Cycle " + cycle + " ---");
            System.out.println("Time: " + (cycle * CYCLE_DURATION_MINUTES) + " minutes");

            // Determine if this is a peak hour (cycles 3-5)
            boolean isPeakHour = cycle >= 3 && cycle <= 5;
            if (isPeakHour) {
                System.out.println("PEAK HOUR: Increased traffic expected");
            }

            // Random events for this cycle
            int arrivals = isPeakHour ? random.nextInt(4) + 1 : random.nextInt(3); // 1-4 arrivals in peak, 0-2 in normal
            int departures = isPeakHour ? random.nextInt(3) : random.nextInt(2); // 0-2 departures in peak, 0-1 in normal

            // Process arrivals
            for (int i = 0; i < arrivals; i++) {
                String planeType = PLANE_TYPES[random.nextInt(PLANE_TYPES.length)];
                String flightNumber = FLIGHT_NUMBERS[random.nextInt(FLIGHT_NUMBERS.length)];
                String destination = DESTINATIONS[random.nextInt(DESTINATIONS.length)];
                
                System.out.println("\nProcessing arrival:");
                Airplane plane = airport.processPlaneArrival(planeType);
                report.recordPlane();
                report.recordPlaneType(planeType);
                
                // Set destination based on plane type
                if (plane instanceof PassengerPlane) {
                    ((PassengerPlane)plane).setDestination(destination);
                } else if (plane instanceof CargoPlane) {
                    ((CargoPlane)plane).setDestination(destination);
                }
                
                planes.add(plane);

                // Random maintenance and refueling needs
                if (random.nextDouble() < 0.3) { // 30% chance of maintenance needed
                    if (plane instanceof PassengerPlane) {
                        ((PassengerPlane)plane).setMaintenanceNeeded(true);
                    } else if (plane instanceof CargoPlane) {
                        ((CargoPlane)plane).setMaintenanceNeeded(true);
                    }
                }
                if (random.nextDouble() < 0.4) { // 40% chance of refueling needed
                    if (plane instanceof PassengerPlane) {
                        ((PassengerPlane)plane).setNeedsRefuel(true);
                    } else if (plane instanceof CargoPlane) {
                        ((CargoPlane)plane).setNeedsRefuel(true);
                    }
                }

                // Add passengers for passenger planes
                if (planeType.equals("Passenger")) {
                    int passengerCount = isPeakHour ? 
                        random.nextInt(10) + 5 : // 5-14 passengers in peak
                        random.nextInt(5) + 1;   // 1-5 passengers in normal
                    
                    for (int j = 0; j < passengerCount; j++) {
                        Passenger passenger = new Passenger(
                            "P" + (passengers.size() + 1),
                            "Passenger " + (passengers.size() + 1),
                            flightNumber
                        );
                        passengers.add(passenger);
                        passenger.checkIn();
                        report.recordPassengerCount(isPeakHour);
                    }
                }
            }

            // Process maintenance and refueling
            for (Airplane plane : planes) {
                if (plane.needsMaintenance()) {
                    plane.performMaintenance();
                }
                if (plane.needsRefueling()) {
                    plane.refuel();
                }
            }

            // Process security screening
            for (Passenger passenger : passengers) {
                if (passenger.isCheckedIn() && !passenger.isSecurityCleared()) {
                    SecurityCheckpoint checkpoint = airport.getAvailableSecurityCheckpoint();
                    if (checkpoint != null) {
                        report.recordSecurityCheckpointUsage(checkpoint);
                        if (checkpoint.processPassenger(passenger)) {
                            passenger.clearSecurity();
                            report.recordSecurityWaitTime(checkpoint.getProcessingTimeMinutes());
                        }
                    } else {
                        System.out.println("No available security checkpoints for passenger " + passenger.getId());
                    }
                }
            }

            // Process departures
            for (int i = 0; i < departures; i++) {
                String planeType = PLANE_TYPES[random.nextInt(PLANE_TYPES.length)];
                System.out.println("\nProcessing departure:");
                airport.processPlaneDeparture(planeType);

                // Board passengers for passenger planes
                if (planeType.equals("Passenger")) {
                    for (Passenger passenger : passengers) {
                        if (!passenger.isBoarded() && passenger.isSecurityCleared()) {
                            passenger.board();
                        }
                    }
                }
            }

            // Print cycle summary
            System.out.println("\nCycle " + cycle + " Summary:");
            System.out.println("- Arrivals: " + arrivals);
            System.out.println("- Departures: " + departures);
            System.out.println("- Total Passengers: " + passengers.size());
            System.out.println("- Checked-in Passengers: " + 
                passengers.stream().filter(Passenger::isCheckedIn).count());
            System.out.println("- Security Cleared Passengers: " + 
                passengers.stream().filter(Passenger::isSecurityCleared).count());
            System.out.println("- Boarded Passengers: " + 
                passengers.stream().filter(Passenger::isBoarded).count());
            System.out.println("- Planes in Airport: " + planes.size());
            System.out.println("- Planes Needing Maintenance: " + 
                planes.stream().filter(Airplane::needsMaintenance).count());
            System.out.println("- Planes Needing Refueling: " + 
                planes.stream().filter(Airplane::needsRefueling).count());
            System.out.println();
        }

        // Generate final report
        report.generateReport();
    }
}
