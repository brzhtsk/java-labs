package main;

public class FireTruck extends SimpleCar<Fireman> {
    public FireTruck(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean allowPassenger(Person p) {
        return p instanceof Fireman;
    }
}