package task;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /*
        Stworzmy klasę Task, która implementuje Callable<Void> oraz niestandardową klasę
        MyFutureTask, która rozszerza FutureTask<Void> i przesłania metodę done() w celu wykonania
        dodatkowych akcji po zakończeniu zadania. Klasa Task przyjmuje pole name do identyfikacji
        zadania. Zadanie ma symylowac dlugotrwala operacje przez uspienie na 2 sekundy np. 3 razy,
        wyswietlajac po kazdym uspieniu komunikat, że zadanie jeszcze się nie zakończyło.
         */

    }


    public static String maptToWords(String text) {
        return Arrays.stream(text.split(""))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static boolean isOnlynumber(String text) {
        return text.chars().allMatch(Character::isDigit);
    }


    public static char getMostFrequencyChar(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(' ');
    }
}
