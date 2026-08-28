package examples.list.string;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>(List.of("Arkadi21usz", "Jakub", "Olo2"));
        List<String> names1 = new ArrayList<>(Arrays.asList("Tomek", "Ania", "Tomek", "Krzys", "Grzes", "Tomek", "Janka"));

        System.out.println(convertToLetter(stringList));
        System.out.println(fromListToMap(stringList));

        System.out.println(withDigitMathod(stringList));
        System.out.println(cleanList(stringList));
        System.out.println(checkFrequency(names1));
    }

    // Napisz metode ktora jako parametr przyjmuje Liste Stringow oraz znak. Zwroc
    // liste zawierajaca wszystkie
    // Stringi ktore zawieraja podana znak
    // Lista: Ania Kasia Grzegorz Tomek Magda a znak to a, to lista zwracana powinna
    // zawierac Ania Kasia Magda

    // Napisz metode ktora jako parametr przyjmuje 2 Listy Stringow i zwraca liste
    // elementów wspolnych, ktore sa na obu listach

    // Napisz metodę, która dla napisu zwraca znak najrzadziej występujący w napisie.
// Jeśli kilka znaków występuje tyle samo razy, zwróć pierwszy z nich.

    // zwroc Listę list gdzie kazda tablica ma elemtyn > 0 i jest posortowana malejaco

    public static int checkFrequency(List<String> list) {
        int tomek = Collections.frequency(list, "Tomek");
        return tomek;
    }

    public static List<String> cleanList(List<String> list) {
        return list.stream()
                .map(Main::removeDigits)
                .toList();
    }

    public static String removeDigits(String text) {
        return text.replaceAll("\\d", "");
    }

    public static List<String> withDigitMathod(List<String> list) {
        return list.stream()
                .filter(s -> s.chars().anyMatch(Character::isDigit))
                .toList();

    }

    public static List<List<Integer>> filterAndSort(List<int[]> list) {
        return list.stream()
                .filter(arr -> Arrays.stream(arr).allMatch(x -> x > 0))
                .filter(arr -> IntStream.range(0, arr.length - 1)
                        .allMatch(i -> arr[i] > arr[i + 1]))
                .map(arr -> Arrays.stream(arr)
                        .boxed().toList())
                .toList();
    }

    public static List<int[]> getListOfArraysWithOddAndSortted(List<int[]> list) {
        return list.stream()
                .filter(arr -> arr.length % 2 == 0)
                .filter(arr -> IntStream.range(0, arr.length - 1)
                        .allMatch(i -> arr[i] < arr[i + 1]))
                .toList();
    }

    public static char minFrequency(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(' ');
    }

    public static char minFrequency2(String text) {
        return Arrays.stream(text.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(e -> e.getKey().charAt(0))
                .orElse(' ');
    }

    public static List<String> findCommonElements(List<String> list1, List<String> list2) {
        return list2.stream()
                .filter(list1::contains)
                .toList();
    }

    // Napisz metodę, która dla tablicy napisów zwraca tylko te napisy, które zawierają co najmniej jedną cyfrę.

    public static String[] getFilterArrayString(String[] arr) {
        return Arrays.stream(arr)
                .filter(s -> s.chars().anyMatch(
                        Character::isDigit))
                .toArray(String[]::new);
    }

    // Napisz metodę, która dla napisu zwraca liczbę samogłosek.
    public static int countVowels(String text) {
        List<Character> vowels = List.of('a', 'ą', 'e', 'ę', 'i', 'o', 'ó', 'u', 'y');

        return (int) Arrays.stream(text.split(""))
                .filter(s -> vowels.contains(s.charAt(0)))
                .count();
    }

    public static List<String> lastCharAre(List<String> list) {
        List<Character> vowels = List.of('a', 'ą', 'e', 'ę', 'i', 'o', 'ó', 'u', 'y');
        return list.stream()
                .filter(s -> s.substring(s.length() - 3).toLowerCase()
                        .chars()
                        .allMatch(e -> vowels.contains((char) e)))
                .toList();

    }

    // Napisz metodę, która liczy ile w danym stringu w liscie jest samoglosek


    // Napisz metodę, która sprawdza, czy podany string składa się wyłącznie z cyfr (0–9).
    public static boolean isDigitMeth(String text) {
        return Optional.ofNullable(text)
                .stream()
                .flatMapToInt(String::chars)
                .allMatch(Character::isDigit);
    }

    public static boolean isDigit2(String text) {
        return Arrays.stream(text.split(""))
                .flatMapToInt(String::chars)
                .allMatch(s -> s >= '0' && s <= '9');
    }

    public static List<String> getListFilter(List<String> list, char c) {
        return list.stream()
                .filter(s -> s.contains(String.valueOf(c)))
                .toList();
    }

    public static List<String> convertToLetter(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .flatMap(s -> Arrays.stream(s.split("")))
                .toList();
    }

    public static Map<String, Integer> fromListToMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        String::length
                ));

    }
}
