package test3.ex1;


import test3.ex1.exceptions.OverwieghtFlightExcpetion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private final String source;
    private final String destination;
    private final String departure;

    private final Airplane airplane;

    private final List<Passenger> standardPassengers;
    private final List<Passenger> premiumPassengers;

    private Flight(Builder builder) {
        this.source = builder.source;
        this.destination = builder.destination;
        this.departure = builder.departure;
        this.airplane = builder.airplane;

        this.standardPassengers = List.copyOf(builder.standardPassengers);
        this.premiumPassengers = List.copyOf(builder.premiumPassengers);
    }

    public static Builder create(String source, String destination, String departure) {
        return new Builder(source, destination, departure);
    }


    public static final class Builder {
        private final String source;
        private final String destination;
        private final String departure;

        private Airplane airplane;

        private final List<Passenger> standardPassengers = new ArrayList<>();
        private final List<Passenger> premiumPassengers = new ArrayList<>();

        public Builder(String source, String destination, String departure) {
            this.source = source;
            if (source.equalsIgnoreCase(destination)) {
                throw new IllegalArgumentException("Source and destination cannot be the same");
            }
            this.destination = destination;
            this.departure = departure;
        }

        public Builder withAirplane(Airplane airplane) {
            this.airplane = airplane;
            return this;
        }

        public Builder withPassenger(Passenger passenger, Seat seatType) {

            if (standardPassengers.contains(passenger) || premiumPassengers.contains(passenger)) {
                throw new IllegalArgumentException("Passenger already assigned to a seat.");
            }
            if (seatType == Seat.STANDARD_SEAT) {
                standardPassengers.add(passenger);
            } else {
                premiumPassengers.add(passenger);
            }

            return this;
        }

        private int calculateTotalWeight() {
            int total = 0;

            for (Passenger p : standardPassengers) {
                total += p.getWeight();
            }
            for (Passenger p : premiumPassengers) {
                total += p.getWeight();
            }
            return total;
        }


        public Flight create() throws OverwieghtFlightExcpetion {

            if (airplane == null) {
                throw new IllegalArgumentException("Airplane must exsit");
            }

            int totalWeight = calculateTotalWeight();

            if (totalWeight > airplane.getCapacityInKilos()) {
                throw new IllegalArgumentException("Passengers exceed weight capacity");
            }

            if (standardPassengers.size() > airplane.getStandardSeats()) {
                throw new IllegalArgumentException("Too many passengers for standard seats");
            }

            if (premiumPassengers.size() > airplane.getPremiumSeats()) {
                throw new IllegalArgumentException("Too many passengers for premium seats");
            }

            return new Flight(this);

        }

    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTimeAndDate() {
        return departure;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public List<Passenger> getStandardPassengers() {
        return standardPassengers;
    }

    public List<Passenger> getPremiumPassengers() {
        return premiumPassengers;
    }

    private int calculateTotalWeight() {
        int total = 0;

        for (Passenger p : standardPassengers) {
            total += p.getWeight();
        }
        for (Passenger p : premiumPassengers) {
            total += p.getWeight();
        }
        return total;
    }


    private BigDecimal percent(int part, int total) {
        if (total == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(part * 100).divide(new BigDecimal(total), 2, RoundingMode.HALF_UP);
    }

    private String premiumSeatsOccupancy() {
        return percent(premiumPassengers.size(), airplane.getPremiumSeats()).toString();
    }

    private String standardSeatsOccupancy() {
        return percent(premiumPassengers.size(), airplane.getStandardSeats()).toString();
    }

    private String premiumPassengersDataPrint() {
        return surnamesData(premiumPassengers);
    }

    private String standardPassengersDataPrint() {
        return surnamesData(standardPassengers);
    }

    private String surnamesData(List<Passenger> passengers) {
        String result = "";
        for (Passenger passenger : passengers) {
            if (!result.isEmpty()) {
                result += ", ";
            }
            result += passenger.getSurname();

        }
        return result;
    }

    private String overWeightRation() {
        int total = calculateTotalWeight();
        return percent(total, airplane.getCapacityInKilos()).toString();
    }

    @Override
    public String toString() {
        return "==== Flight from: " + source.toUpperCase() + " to " + destination.toUpperCase() + " departures on: " + departure + " ==="
                + "\nAirplane: " + airplane.getName()
                + "\nPREMIUM SEATS OCCUPANCY: " + premiumSeatsOccupancy() + " %"
                + "\nPREMIUM SEAT PASSENGERS: " + premiumPassengersDataPrint()
                + "\nSTANDARD SEATS OCCUPANCY: " + standardSeatsOccupancy() + " %"
                + "\nSTANDARD SEAT PASSENGERS: " + standardPassengersDataPrint()
                + "\nOVERWEIGHT RATION: " + overWeightRation() + " % ";
    }


}
