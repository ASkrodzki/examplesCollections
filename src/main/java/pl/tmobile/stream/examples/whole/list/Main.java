package pl.tmobile.stream.examples.whole.list;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>(Arrays.asList(5, 10, 15, -5, -10, -15, 20, 20, 25, -5, 5));
        List<String> stringList = new ArrayList<>(List.of("AREK", "Arkadiusz", "Tomek", "Tomek", "Ala", "jakub", "Jan", "A213k", "23"));

        List<int[]> listOfArrays = List.of(
                new int[]{5, 54, 3, 4},
                new int[]{1, 2, 3, 4},
                new int[]{1, 3, 2, 4},
                new int[]{2, 2, 6, 8, 10, 12},
                new int[]{4, 2, 6, 8, 10},
                new int[]{2, 2, 6, 18, 16},
                new int[]{1, 2, 3}
        );
        //Napisz metodę, która z listy Integer zwróci nową listę, gdzie każda liczba jest pomnożona przez 2.

        System.out.println("EX1");
        System.out.println(doubleValueList(integerList));
        System.out.println(doubleValueList2(integerList));
        System.out.println(doubleValueList3(integerList));

        //Napisz metodę, która zwróci listę kwadratów liczb.
        System.out.println("EX2");
        System.out.println(powList(integerList));

        //Napisz metodę, która do każdego stringa dopisze znak *.

        System.out.println("EX3");
        System.out.println(appendSufix(stringList, "extra"));

        System.out.println("EX4");
        System.out.println(removeNegativeNumbers(integerList));
        System.out.println(removeNegativeNumbers2(integerList));

        //Zwróć wszystkie liczby większe od 100.
        System.out.println("EX5 ");
        System.out.println(biggerThan5(integerList));

        System.out.println("EX6");
        System.out.println(startWith(stringList));
        System.out.println(startWithBigLetter(stringList));

        System.out.println("EX7");
        System.out.println(reduceSumNumbers(integerList));
        System.out.println(mapToIntSum(integerList));
        System.out.println(mapToIntIloczyn(integerList));
        System.out.println(sumOfString(stringList));

        System.out.println("EX8");
        System.out.println(distinctList(stringList));
    }

    public static List<Integer> doubleValueList(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        result.replaceAll(x -> x * 2);
        return result;
    }

    public static List<Integer> doubleValueList2(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(i * 2);
        }
        return result;
    }

    public static List<Integer> doubleValueList3(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .map(x -> x * 2)
                .toList();
    }

    public static List<Integer> powList(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .map(x -> (int) Math.pow(x, 2))
                .toList();
    }

    private static List<String> appendSufix(List<String> list, String c) {
        List<String> result = new ArrayList<>(list);
        result.replaceAll(x -> x + c);
        return result;
    }

    private static List<Integer> removeNegativeNumbers(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(x -> x > 0)
                .toList();
    }

    private static List<Integer> removeNegativeNumbers2(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        result.removeIf(x -> x < 0);
        return result;
    }

    private static List<Integer> biggerThan5(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(x -> x > 5)
                .toList();
    }

    public static List<String> startWith(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(x -> Character.isUpperCase(x.charAt(0)))
                .toList();
    }

    public static List<String> startWithBigLetter(List<String> list) {
        List<String> result = new ArrayList<>();

        for (String s : list) {
            if (Character.isUpperCase(s.charAt(0))) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String> startWithLowerCase(List<String> list) {
        return list.stream()
                .filter(s -> Character.isLowerCase(s.charAt(0)))
                .toList();
    }

    public static List<String> endWtihUpperCase(List<String> list) {
        return list.stream().filter(s -> Character.isUpperCase(s.charAt(s.length() - 1))).toList();
    }

    public static List<String> endWordsEndOnA(List<String> list) {
        return list.stream().filter(s -> s.endsWith("e")).toList();
    }

    public static List<String> containsLetter(List<String> list) {
        return list.stream().filter(s -> s.contains("a")).toList();
    }

    public static List<String> upperCaseWords(List<String> list) {
        return list.stream().filter(s -> s.equals(s.toUpperCase())).toList();
    }

    public static List<String> lowerCaseWords(List<String> list) {
        return list.stream().filter(s -> s.equals(s.toLowerCase())).toList();
    }

    public static List<String> findWordsStartAndEndOnTheSameLetter(List<String> list) {
        return list.stream().filter(s -> s.charAt(0) == s.charAt(s.length() - 1)).toList();
    }

    public static List<String> onlyDigit(List<String> list) {
        return list.stream().filter(s -> s.matches("\\d+")).toList();
    }

    public static List<String> onlyLetters(List<String> list) {
        return list.stream().filter(s -> s.matches("[a-zA-Z]+")).toList();
    }

    public static List<String> onlyDigit2(List<String> list) {
        return list.stream()
                .filter(s -> s.chars()
                        .allMatch(Character::isDigit))
                .toList();
    }

    public static List<String> onlyLetters2(List<String> list) {
        return list.stream()
                .filter(s -> !s.isEmpty())
                .filter(s -> s.chars()
                        .allMatch(Character::isLetter))
                .toList();
    }

    public static List<String> uniqueChars(List<String> list) {
        return list.stream()
                .filter(s -> s.chars().distinct().count() == s.length())
                .toList();
    }

    //znajdz wyraz zwierajcay przynajmnie jedna litere z drugiej listy

    public static List<String> methodFirst(List<String> list1, List<String> list2) {
        return list1.stream()
                .filter(l1 -> list2.stream()
                        .anyMatch(l2 -> l1.toLowerCase().contains(l2.toString())))
                .toList();
    }

    public static List<String> noneLetters(List<String> names, List<String> letters) {
        return names.stream().filter(name -> letters.stream()
                        .noneMatch(letter -> name.contains(letter.toString())))
                .toList();
    }

    public static List<Integer> sort1(List<Integer> list) {
        return list.stream()
                .sorted()
                .toList();
    }

    public static Integer reduceSumNumbers(List<Integer> list) {
        return list.stream()
                .reduce((a, b) -> a + b).orElse(null);
    }

    public static Integer mapToIntSum(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Integer mapToIntIloczyn(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .reduce(1, (a, b) -> a * b);
    }

    public static Integer sumOfString(List<String> list) {
        return list.stream()
                .mapToInt(String::length)
                .sum();
    }

    public static List<String> distinctList(List<String> list) {
        return list.stream()
                .distinct()
                .toList();

    }

    public static List<String> uniqueOnly(List<String> list) {
        return list.stream()
                .filter(s -> Collections.frequency(list, s) == 1)
                .toList();
    }

    public static List<String> uniqueOnly2(List<String> list) {
        Map<String, Long> collect = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return list.stream()
                .filter(s -> collect.get(s) == 1)
                .toList();
    }

    public static int[] flatMapList(List<int[]> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .flatMapToInt(Arrays::stream)
                .sorted()
                .toArray();
    }

}
