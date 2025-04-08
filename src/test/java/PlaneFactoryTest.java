import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Factory Method Design Pattern

public class PlaneFactoryTest {

    @Test
    public void testCreatePassengerPlane() {
        PlaneFactory planeFactory = new PlaneFactory();
        Plane plane = planeFactory.createPlane("Passenger");

        // Assert the plane is a PassengerPlane
        assertTrue(plane instanceof PassengerPlane);
    }

    @Test
    public void testCreateCargoPlane() {
        PlaneFactory planeFactory = new PlaneFactory();
        Plane plane = planeFactory.createPlane("Cargo");

        // Assert the plane is a CargoPlane
        assertTrue(plane instanceof CargoPlane);
    }

    @Test
    public void testCreateInvalidPlane() {
        PlaneFactory planeFactory = new PlaneFactory();

        // Assert that an invalid plane type throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            planeFactory.createPlane("Unknown");
        });
    }
}