package main;

public class Taxi extends SimpleCar<Person> {
    public Taxi(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean allowPassenger(Person p) {
        return true;
    }
}
