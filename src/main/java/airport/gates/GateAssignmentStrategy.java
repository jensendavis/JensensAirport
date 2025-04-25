package airport.gates;

import airport.airplanes.Airplane;
import java.util.List;

public interface GateAssignmentStrategy {
    Gate assignGate(Airplane plane, List<Gate> availableGates);
} 