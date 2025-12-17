package main;

public class PoliceVehicle extends SimpleCar<Cop> {
    public PoliceVehicle(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean allowPassenger(Person p) {
        return p instanceof Cop;
    }
}