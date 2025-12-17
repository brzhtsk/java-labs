package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransportTests {

    @Test
    void busAcceptsAnyPerson() {
        Bus bus = new Bus(3);
        bus.board(new RegularPerson("Anna"));
        bus.board(new Fireman("Petro"));
        bus.board(new Cop("Oleg"));
        assertEquals(3, bus.getOccupiedSeats());
    }

    @Test
    void taxiAcceptsAnyPerson() {
        Taxi taxi = new Taxi(2);
        taxi.board(new RegularPerson());
        taxi.board(new Cop());
        assertEquals(2, taxi.getOccupiedSeats());
    }

    @Test
    void policeVehicleOnlyAllowsCops() {
        PoliceVehicle pv = new PoliceVehicle(2);
        pv.board(new Cop("Іван"));

        SimpleCar rawPv = pv;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rawPv.board(new Fireman("Петро"));
        });
        assertTrue(exception.getMessage().contains("не дозволений"));
    }

    @Test
    void fireTruckOnlyAllowsFiremen() {
        FireTruck ft = new FireTruck(2);
        ft.board(new Fireman("Сергій"));

        SimpleCar rawFt = ft;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rawFt.board(new RegularPerson("Олена"));
        });
        assertTrue(exception.getMessage().contains("не дозволений"));
    }

    @Test
    void fullVehicleThrows() {
        Taxi taxi = new Taxi(1);
        taxi.board(new RegularPerson());
        assertThrows(RuntimeException.class, () -> taxi.board(new Cop()));
    }

    @Test
    void disembarkNonPassengerThrows() {
        Bus bus = new Bus(2);
        assertThrows(RuntimeException.class, () -> bus.disembark(new RegularPerson()));
    }

    @Test
    void roadCountsAllPassengers() {
        Road road = new Road();

        Bus bus = new Bus(2);
        bus.board(new RegularPerson());
        bus.board(new Cop());

        PoliceVehicle pv = new PoliceVehicle(1);
        pv.board(new Cop());

        FireTruck ft = new FireTruck(1);
        ft.board(new Fireman());

        road.addVehicle(bus);
        road.addVehicle(pv);
        road.addVehicle(ft);

        assertEquals(4, road.getTotalPassengers());
    }
}