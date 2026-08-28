package test3.ex1;

public class Airplane {
    private final String name;
    private final int capacityInKilos;
    private final int standardSeats;
    private final int premiumSeats;

    public Airplane(String name, int capacityInKilos, int standardSeats, int premiumSeats) {
        this.name = name;
        if (standardSeats < 0 || premiumSeats < 0 || capacityInKilos <= 0) {
            throw new IllegalArgumentException("wrong values of airplane");
        }

        this.capacityInKilos = capacityInKilos;
        this.standardSeats = standardSeats;
        this.premiumSeats = premiumSeats;
    }

    public String getName() {
        return name;
    }

    public int getCapacityInKilos() {
        return capacityInKilos;
    }


    public int getStandardSeats() {
        return standardSeats;
    }


    public int getPremiumSeats() {
        return premiumSeats;
    }


    @Override
    public String toString() {
        return name + " " + capacityInKilos + " " + standardSeats + " " + premiumSeats;
    }
}
