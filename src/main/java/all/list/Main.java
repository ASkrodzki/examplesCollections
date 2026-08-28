package all.list;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.tmobile.stream.examples.mac.test.repeat.list.Main.getOddAndEvenList;

public class Main {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>(List.of("Arek", "kuba", "Marcin", " ", "Ala"));
        List<Integer> integerList = new ArrayList<>(List.of(5, 10, 20, 3, 14, 8, 20, 7, 0, 14, 0));
        List<Integer> integerList2 = new ArrayList<>(List.of(5, 10, 0, 14, 0));
        //Zwróć liczbę elementów w liście.
        System.out.println(count(integerList));
        //Zwróć listę liczb parzystych.
        System.out.println(evenList(integerList));


        //Zwróć listę liczb większych niż podana wartość.
        //Oblicz sumę wszystkich liczb z listy.
        System.out.println(sumOfElements(integerList));
        //Oblicz sumę liczb nieparzystych.
        //Oblicz iloczyn liczb z listy.
        //Oblicz średnią z listy liczb.
        System.out.println(averageNumbers(integerList));
        //Znajdź najmniejszą liczbę w liście.
        //Znajdź największą liczbę w liście.

        System.out.println(lowerNumber(integerList));
        //Znajdź drugą największą liczbę w liście.
        //Posortuj liczby rosnąco.
        //Posortuj liczby malejąco.
        //Usuń duplikaty z listy.
        //Policz, ile liczb jest większych od 50.
        System.out.println(countBiggeThan50(integerList));
        //Sprawdź, czy istnieje liczba ujemna.
        System.out.println(isExistNegativeNumber(integerList));
        //Sprawdź, czy żadna liczba nie jest równa 0.
        //Podziel liczby na parzyste i nieparzyste.
        System.out.println(divideOnEvenAndOdd(integerList));
        //Zwróć top N największych liczb.
        //Pobierz pierwsze N elementów listy.
        //Pomiń pierwsze N elementów listy.
        //Zwróć wspólne elementy dwóch list.
        System.out.println(commoElements(integerList, integerList2));
        //Wyczyść listę z null, pustych stringów i posortuj.

        System.out.println(cleanList(stringList));
        //Zwróć listę stringów dłuższych niż 5, zaczynających się wielką literą.
        System.out.println(stringsLongerThan3(stringList));
        //Znajdź najdłuższy napis w liście.
        //Zwróć palindromy z listy stringów.
        System.out.println(palindromeFromList(stringList));
        //Połącz listę napisów w jeden tekst oddzielony przecinkami.
        System.out.println(connectList(stringList));
        //Zamień wszystkie napisy na wielkie litery.
        System.out.println(toUpperCaseMethod(stringList));
        //Utwórz listę długości napisów.
        System.out.println(listOfValueLength(stringList));
        //Posortuj napisy malejąco alfabetycznie.
        //Znajdź pierwszy napis zaczynający się na "A".
        System.out.println(findFirstStringStartWithA(stringList));
        //Sprawdź, czy wszystkie napisy mają długość większą niż 3.
        System.out.println(allLongetThan3Check(stringList));
        //Wyciągnij wszystkie unikalne litery z listy słów.
        System.out.println("-----");
        System.out.println(uniqueLetterFromWordsForThisWord(stringList));
        System.out.println(uniqueLetterFromWords(stringList));

        System.out.println(getOddAndEvenList(integerList2));
    }

    private static List<String> uniqueLetterFromWords(List<String> stringList) {
        return stringList.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static List<List<String>> uniqueLetterFromWordsForThisWord(List<String> stringList) {
        return stringList.stream()
                .map(s -> Arrays.stream(s.split(""))
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )).entrySet()
                        .stream()
                        .filter(e -> e.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .toList()
                ).toList();
    }

    private static boolean allLongetThan3Check(List<String> stringList) {
        return stringList.stream()
                .allMatch(s -> s.length() > 3);
    }

    private static String findFirstStringStartWithA(List<String> stringList) {
        return stringList.stream()
                .filter(s -> Character.toLowerCase(s.charAt(0)) == 'a')
                .findFirst().orElse(null);
    }

    private static String findFirstStringStartWithA2(List<String> stringList) {
        return stringList.stream()
                .filter(s -> s.toLowerCase().startsWith("a"))
                .findFirst().orElse(null);
    }

    private static List<Integer> listOfValueLength(List<String> stringList) {
        return stringList.stream()
                .map(s -> s.length())
                .toList();
    }

    private static List<String> toUpperCaseMethod(List<String> stringList) {
        return stringList.stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                // .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .toList();
    }

    private static String connectList(List<String> stringList) {
        return stringList.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .collect(Collectors.joining(","));
    }

    private static List<String> palindromeFromList(List<String> stringList) {
        return stringList.stream()
                .filter(s -> !s.isBlank())
                .filter(s -> new StringBuilder(s).reverse().toString().equalsIgnoreCase(s))
                .toList();
    }


    private static String stringsLongerThan3(List<String> stringList) {
        return stringList.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

    private static String stringsLongerThan32(List<String> stringList) {
        return stringList.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().orElse(null);
    }

    public static List<String> cleanList(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .sorted()
                .toList();
    }

    private static List<Integer> commoElements(List<Integer> integerList, List<Integer> integerList2) {
        List<Integer> result = new ArrayList<>();
        if (integerList == null || integerList2 == null) {
            throw new IllegalArgumentException("cannot be null");
        }

        for (Integer number : integerList) {
            if (number != null && integerList2.contains(number)) {
                result.add(number);
            }
        }
        return result;
    }

    private static List<Integer> commoElements3(List<Integer> integerList, List<Integer> integerList2) {
        List<Integer> list2 = Optional.ofNullable(integerList2)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(i -> i != null)
                .toList();

        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(i -> i != null)
                .filter(i -> list2.contains(i))
                .toList();
    }

    private static List<Integer> commoElements2(List<Integer> integerList, List<Integer> integerList2) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(l -> Optional.ofNullable(integerList2)
                        .orElseGet(Collections::emptyList)
                        .stream().filter(Objects::nonNull)
                        .anyMatch(i -> l.equals(i)))
                .toList();
    }

    private static List<List<Integer>> divideOnEvenAndOdd(List<Integer> integerList) {
        Map<Boolean, List<Integer>> map = Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.partitioningBy(
                        n -> n % 2 == 0));
        return List.of(map.get(true), map.get(false));
    }

    private static List<List<Integer>> divideOnEvenAndOdd2(List<Integer> integerList) {

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        for (Integer i : integerList) {
            if (i % 2 == 0) {
                even.add(i);
            } else {
                odd.add(i);
            }
        }
        return List.of(even, odd);
    }

    private static boolean isExistNegativeNumber(List<Integer> integerList) {
        return integerList.stream()
                .anyMatch(x -> x < 0);
    }

    private static int countBiggeThan50(List<Integer> integerList) {
        return (int) Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(n -> n > 10)
                .count();

    }

    private static int lowerNumber(List<Integer> integerList) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("lista jest pusta"));
    }

    private static long count(List<Integer> integerList) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .count();

    }

    private static double averageNumbers(List<Integer> integerList) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average().orElse(0);

    }

    private static int sumOfElements(List<Integer> integerList) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<Integer> evenList(List<Integer> integerList) {
        return Optional.ofNullable(integerList)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(x -> x % 2 == 0)
                .toList();

    }
}
