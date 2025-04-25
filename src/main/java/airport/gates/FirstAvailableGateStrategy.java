package airport.gates;

import airport.airplanes.Airplane;
import java.util.List;

public class FirstAvailableGateStrategy implements GateAssignmentStrategy {
    @Override
    public Gate assignGate(Airplane plane, List<Gate> availableGates) {
        if (availableGates.isEmpty()) {
            throw new RuntimeException("No gates available");
        }
        return availableGates.get(0);
    }
} 