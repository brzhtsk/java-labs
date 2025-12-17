package main;

public class Bus extends Transport<Person> {
    public Bus(int capacity) {
        super(capacity);
    }

    @Override
    public void board(Person passenger) {
        if (getOccupiedSeats() >= getCapacity()) {
            throw new RuntimeException("Автобус повний!");
        }
        getPassengers().add(passenger);
    }

    @Override
    public void disembark(Person passenger) {
        if (!getPassengers().contains(passenger)) {
            throw new RuntimeException("Цього пасажира немає в автобусі!");
        }
        getPassengers().remove(passenger);
    }
}
