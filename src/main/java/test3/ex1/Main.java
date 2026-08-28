package test3.ex1;

public class Main {
    public static void main(String[] args) {

               /*
        Zadanie 01:
Stworz klasę Airplane (name, capacity in kilos, standard seats, premium seats)
Stworz klasę Passenger (name, surname, weight)
Stworz klasę Flight (source, destination, departure time &
date)

Instancja klasy Flight powinna być tworzona w formie builder patterna np:
Flight flight = Flight.create(source, destination, departure)
 .withAirplane(airplane)
 .withPassenger(p1, SeatType.STANDARD)
 .withPassenger(p2, SeatType.PREMIUM)
 .create();

Teraz uwaga, zgodnie z tym co śpiewała Anita Lipnicka "Wszystko się może zdarzyć"
a zatem metode terminująca buildera .create() może albo stworzyć obiekt Flight
albo zasygnalizować jeden z powyższych wyjątków:
- OverweightFlightException - rzucony wtedy gdy waga przekazanych pasazerow jest
 wieksza niz capacityInKilos w samolocie.
- ExceedStandardSeatsException - rzucony wtedy gdy dodalismy wiecej pasazerow na
miejsca typu standard niz jest ich w samolocie.
- ExceedPremiumSeatsException - rzucony wtedy gdy dodalismy wiecej pasazerow na
 miejsca premium niz jest ich w samolocie.
Klasa Flight powinna być w PEŁNI immutable.

Natomiast jeśli instancja flight zostanie stworzona to wypisanie jej na konsoli
powinno wyglądac w następujący sposób:
==== Flight from: SOURCE to DESTINATION departures on: DEPARTURE_DATE_TIME ===
Airplane: AIRPLANE_NAME
PREMIUM SEATS OCCUPANCY: xx %
PREMIUM SEAT PASSENGERS: SURNAME1, SURNAME2, ... SURNAMEN
STANDARD SEATS OCCUPANCY: yy %
STANDARD SEAT PASSENGERS: SURNAME1, SURNAME2, ... SURNAMEN
OVERWEIGHT RATION: zz %

UWAGA: specyfikacja zadania jest obowiązkowa, forma wydruku też.
         */
        Airplane airplane = new Airplane("Pudzian545LotniskowiecBojowy", 1500, 15, 5);

        Passenger p1 = new Passenger("Tomasz", "Tomaszewski", 75);
        Passenger p2 = new Passenger("Tomisław", "Kowalski", 75);


        Flight flight1 = Flight.create("Modlin", "Dublin", "2025-12-16 18:45")
                .withAirplane(airplane)
                .withPassenger(p1, Seat.STANDARD_SEAT)
                .withPassenger(p2, Seat.PREMIUM_SEAT)
                .create();

        System.out.println(flight1);


    }
}
