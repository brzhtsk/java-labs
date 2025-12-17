package main;

public class Main {
    public static void main(String[] args) {
        Cop cop = new Cop("Іван");
        PoliceVehicle policeCar = new PoliceVehicle(3);
        policeCar.board(cop);

        System.out.println("Поліцейська машина: зайнято місць " + policeCar.getOccupiedSeats());
    }
}