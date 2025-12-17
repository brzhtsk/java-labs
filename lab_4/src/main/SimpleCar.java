package main;

public abstract class SimpleCar<T extends Person> extends Transport<T> {
    public SimpleCar(int capacity) {
        super(capacity);
    }

    @Override
    public void board(T passenger) {
        if (getOccupiedSeats() >= getCapacity()) {
            throw new RuntimeException("Немає вільних місць!");
        }
        if (!allowPassenger(passenger)) {
            throw new RuntimeException("Такий пасажир не дозволений!");
        }
        getPassengers().add(passenger);
    }

    @Override
    public void disembark(T passenger) {
        if (!getPassengers().contains(passenger)) {
            throw new RuntimeException("Цього пасажира немає в машині!");
        }
        getPassengers().remove(passenger);
    }

    protected abstract boolean allowPassenger(Person p);
}