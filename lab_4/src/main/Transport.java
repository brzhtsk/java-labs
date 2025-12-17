package main;

import java.util.HashSet;
import java.util.Set;

public abstract class Transport<T extends Person> {
    private final int capacity;
    private final Set<T> passengers = new HashSet<>();

    public Transport(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    protected Set<T> getPassengers() {
        return passengers;
    }

    public abstract void board(T passenger);

    public abstract void disembark(T passenger);
}