package main;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private final List<Transport<? extends Person>> vehicles = new ArrayList<>();

    public void addVehicle(Transport<? extends Person> vehicle) {
        vehicles.add(vehicle);
    }

    public int getTotalPassengers() {
        int total = 0;
        for (Transport<? extends Person> v : vehicles) {
            total += v.getOccupiedSeats();
        }
        return total;
    }

    public List<Transport<? extends Person>> getVehicles() {
        return new ArrayList<>(vehicles);
    }
}