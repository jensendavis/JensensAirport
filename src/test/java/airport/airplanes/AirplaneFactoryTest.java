package airport.airplanes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneFactoryTest {
    @Test
    public void testCreatePassengerPlane() {
        AirplaneFactory planeFactory = new AirplaneFactory();
        Airplane plane = planeFactory.createAirplane("Passenger");
        assertNotNull(plane);
        assertTrue(plane instanceof PassengerPlane);
    }

    @Test
    public void testCreateCargoPlane() {
        AirplaneFactory planeFactory = new AirplaneFactory();
        Airplane plane = planeFactory.createAirplane("Cargo");
        assertNotNull(plane);
        assertTrue(plane instanceof CargoPlane);
    }

    @Test
    public void testCreateUnknownPlane() {
        AirplaneFactory planeFactory = new AirplaneFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            planeFactory.createAirplane("Unknown");
        });
    }
} 