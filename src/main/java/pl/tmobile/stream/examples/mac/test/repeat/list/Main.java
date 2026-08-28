package pl.tmobile.stream.examples.mac.test.repeat.list;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>(Arrays.asList("Kawa", "pies", "pies", "ALA", "Abba", " ", "jamnik", "Arkadiusz", "Annnna"));
        List<Integer> integerList = new ArrayList<>(Arrays.asList(5, 10, 12, 15, 19, 23, 41));
        List<Integer> integerList2 = new ArrayList<>(Arrays.asList(5, 10));

        List<List<Integer>> listOfList = Arrays.asList(List.of(3, 7, 5, 7), null, List.of(1, 4, 5));

        //  System.out.println(multiply(integerList));

        String text1 = "ala ma Kota i PiesKa";
        System.out.println(mapToWordsStartBigLetter(text1));

        System.out.println(mapToWordsStartLowerLetter(text1));

        System.out.println(countWords(text1));
        System.out.println(longestWord(text1));

        System.out.println(reverseWords(text1));

        System.out.println("6.");
        System.out.println(wordsStartWithBigletter(text1));

        System.out.println(removeIfShorterThan4(text1));

        System.out.println(changeFirstAndLastLetter(text1));

        System.out.println(stringToCamelCase(text1));
        System.out.println(stringToSnakeCase(text1));

        System.out.println(isAllStartsLetter(text1));

        System.out.println("-18.06-");
        int[] intArray = new int[]{5, 10, 25, 12, 14, 5, 6};
        String[] names = new String[]{"Arkadiusz Skrodzki", "Jan Nowak"};

        String name = "Jan Kowalski";
        System.out.println(initials(name));
        String[] strings = initialsArr(names);
        System.out.println(Arrays.toString(strings));
        System.out.println(Arrays.toString(initialsArray2(names)));

        System.out.println(changeFirstAndLastLetter2(name));
        System.out.println(Arrays.toString(changeFirstAndLastLetterArray(names)));
        System.out.println(isPositive(intArray));

        System.out.println(sumOfDigits(intArray));

        List<int[]> listOfArrayInt = new ArrayList<>(List.of(new int[]{5, 10, 50}, new int[]{5, 10, 50}, new int[]{3, 7}));

        getPrimaryArrays(listOfArrayInt).forEach(tab -> System.out.println(Arrays.toString(tab)));

        System.out.println(sumOfDigits(intArray));
    }

    public static int sumOfLetterLength(String[] arr) {
        return Arrays.stream(arr)
                .mapToInt(String::length)
                .sum();
    }

    private static int sumOfDigitsFromArray(int[] arr) {
        return Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .flatMap(s -> Arrays.stream(s.split("")))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static List<int[]> getPrimaryArrays(List<int[]> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(x -> primaryInt(x))
                .collect(Collectors.toList());
    }

    private static boolean primaryInt(int[] arr) {
        return Arrays.stream(arr)
                .allMatch(Main::isPrimeMethod);
    }

    public static boolean isPrimeMethod(int n) {
        if (n <= 1) {
            return false;
        }
        return !IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .anyMatch(i -> n % i == 0);
    }

    public static int sumOfDigits(int[] arr) {
        return Arrays.stream(arr).map(Main::sumOfDigAtInt).sum();
    }

    public static int sumOfDigAtInt(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static boolean isPositive(int[] arr) {
        return Arrays.stream(arr).allMatch(x -> x > 0);
    }


    private static String initials(String name) {
        return Arrays.stream(name.split(" ")).map(s -> String.valueOf(s.charAt(0))).collect(Collectors.joining());
    }

    public static String[] changeFirstAndLastLetterArray(String[] arr) {
        return Arrays.stream(arr)
                .map(name -> Arrays
                        .stream(name.split(" "))
                        .map(s -> {
                            if (s.length() < 2) {
                                return s;
                            }
                            return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
                        }).collect(Collectors.joining(" "))).toArray(String[]::new);
    }

    private static String changeFirstAndLastLetter2(String text1) {
        return Arrays.stream(text1.split(" ")).map(s -> {
            if (s.length() < 2) {
                return s;
            }
            return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
        }).collect(Collectors.joining(" "));
    }

    public static String[] initialsArray2(String[] arr) {
        return Arrays.stream(arr).map(name -> Arrays.stream(name.split(" ")).map(s -> String.valueOf(s.charAt(0))).collect(Collectors.joining(".")) + ".").toArray(String[]::new);
    }

    public static String[] initialsArr(String[] arr) {
        return Arrays.stream(arr).map(name -> {
            String[] parts = name.split(" ");

            return "" + parts[0].charAt(0) + "." + parts[1].charAt(0) + ".";
        }).toArray(String[]::new);
    }

    public static String longestWord(String[] arr) {
        return Arrays.stream(arr).sorted(Comparator.comparingInt(String::length)).findFirst().orElseGet(null);
    }

    public static int sumOfEven(int[] arr) {
        return Arrays.stream(arr).filter(x -> x % 2 == 0).sum();
    }

    public static int sumOfEven(Integer[] arr) {
        return Arrays.stream(arr).mapToInt(Integer::intValue).filter(x -> x % 2 == 0).sum();
    }

    private static boolean isAllStartsLetter(String text1) {
        return Arrays.stream(text1.split(" ")).allMatch(s -> Character.isUpperCase(s.charAt(0)));

    }

    private static String stringToSnakeCase(String text1) {
        return String.join("_", text1.split(" "));
    }

    private static String stringToCamelCase(String text1) {
        String[] words = text1.toLowerCase().split(" ");
        return words[0] + Arrays.stream(words).skip(1).map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1)).collect(Collectors.joining(""));
    }


    private static String changeFirstAndLastLetter(String text1) {
        return Arrays.stream(text1.split(" ")).map(s -> {
            if (s.length() < 2) {
                return s;
            }
            return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
        }).collect(Collectors.joining(" "));
    }

    private static String removeIfShorterThan4(String text1) {
        return Arrays.stream(text1.split(" ")).filter(s -> s.length() > 4).collect(Collectors.joining(" "));
    }

    private static String wordsStartWithBigletter(String text1) {
        return Arrays.stream(text1.split(" ")).filter(s -> s.startsWith(String.valueOf(Character.toUpperCase(s.charAt(0))))).collect(Collectors.joining(" "));

    }

    private static String reverseWords(String text1) {
        List<String> words = Arrays.asList(text1.split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }


    private static String longestWord(String text1) {
        return Arrays.stream(text1.split(" ")).sorted().findFirst().orElseGet(null);

    }

    public static long countWords(String text) {
        return Arrays.stream(text.split("")).filter(s -> !s.equals(" ")).count();
    }

    public static String mapToWordsStartLowerLetter(String text) {
        return Arrays.stream(text.split(" ")).map(s -> Character.toLowerCase(s.charAt(0)) + s.substring(1)).collect(Collectors.joining(" "));
    }

    public static String mapToWordsStartBigLetter(String text) {

        return Arrays.stream(text.split(" ")).map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1)).collect(Collectors.joining(" "));

        //        StringBuilder sb = new StringBuilder(text);
//        for (int i = 0; i < sb.length(); i++) {
//            if (i == 0 || sb.charAt(i - 1) == ' ') {
//                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
//            }
//        }
//        return sb.toString();

        //        ArrayList<String> strings = new ArrayList<>(List.of(text.split(" ")));
//        strings.replaceAll(
//                s -> Character.toUpperCase(s.charAt(0))
//                        + s.substring(1)
//        );
//        return String.join(" ", strings);

        // String[] words = text.split(" ");
//        for (int i = 0; i < words.length; i++) {
//            if (i == 0 || words[i - 1] == " ") {
//                words[i] = Character.toUpperCase(words[i].charAt(0))
//                        + words[i].substring(1);
//            }
//        }
//        return String.join(" ", words);

        //        return Arrays.stream(text.split(" "))
//                .map(s -> Character.toUpperCase(s.charAt(0))
//                        + s.substring(1))
//                .collect(Collectors.joining(" "));
//


    }


    public static int multiply(List<Integer> list) {
        return list.stream().reduce(1, (a, b) -> a * b);

    }

    public static List<String> startWithGreateLetter(List<String> list) {
        return list.stream().filter(s -> Character.isUpperCase(s.charAt(0))).toList();
    }

    public static Map<String, Long> countWordsStartWithLetter(List<String> list) {
        return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

    public static List<String> listStringLengthGreaterThan5(List<String> list) {
        return list.stream().filter(s -> s.length() > 5).toList();
    }

    public static String longestString(List<String> list) {
        return list.stream().max(Comparator.comparingInt(String::length)).orElseGet(null);
    }

    public static List<Integer> getFlatListAndSortListOfList(List<List<Integer>> list) {
        return list.stream().flatMap(Collection::stream).sorted().toList();
    }

    public static List<String> removeDuplicates(List<String> list) {
        return list.stream().distinct().toList();

    }

    public static List<String> pickUniqueWords(List<String> list) {

        Map<String, Long> resultMap = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return list.stream()
                .filter(s -> resultMap.get(s) == 1)
                .toList();
    }

    public static List<String> pickUniqueWords2(List<String> list) {
        return list.stream().filter(s -> Collections.frequency(list, s) == 1).toList();
    }

    public static List<Integer> commonList2(List<Integer> list1, List<Integer> list2) {

        return Optional.ofNullable(list1).orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull).filter(e -> Optional.ofNullable(list2)
                        .orElseGet(Collections::emptyList)
                        .contains(e)).toList();

        //filter(list2.::contains).toList()

    }

    public static List<List<Integer>> getListofListWithFilter(List<List<Integer>> list) {
        return Optional.of(list).orElseGet(Collections::emptyList).stream().filter(Objects::nonNull)
                .filter(sub -> sub.size() > 3)
                .filter(sub -> sub.stream().
                        allMatch(x -> x > 1)).toList();

    }

    public static List<List<Integer>> getOddAndEvenList(List<Integer> list) {

        Map<Boolean, List<Integer>> map = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));

        return Arrays.asList(map.get(true), map.get(false));

    }

    public static List<Integer> concatList1(List<List<Integer>> list1) {
        List<Integer> result = list1.stream().flatMap(List::stream).toList();

        return result;
    }

    public static List<Integer> concatList(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = Stream.concat(list1.stream(), list2.stream()).toList();

        return result;
    }

    public static List<String> getCleanList(List<String> list) {
        return list.stream().filter(s -> !s.isBlank()).sorted(Comparator.comparingInt(String::length)).toList();
    }

    public static long findSecondBiggestNumber(List<Integer> list) {
        return list.stream().sorted().skip(1).findFirst().orElseGet(() -> Integer.valueOf("brak"));
    }

    //    // zwroc lsite list gdzie kazda tablica ma elemtyn > 0 i jest posortowana malejaco
    public static List<int[]> getListOfArray(List<int[]> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().filter(Objects::nonNull).filter(l -> Arrays.stream(l).allMatch(i -> l[i] > 1)).filter(l -> IntStream.range(0, l.length - 1).allMatch(i -> l[i] > l[i + 1])).toList();
    }


    public static List<String> getPalindroms(List<String> list) {
        return list.stream().filter(text -> text.equals(new StringBuilder(text).reverse().toString())).toList();

    }

    public static List<String> commonList(List<String> list, List<String> list2) {

        return list.stream().filter(list2::contains).toList();
    }

    public static Map<String, Long> countLettersInWords(List<String> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().map(s -> s.split("")).flatMap(Arrays::stream).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

//Waldek!

    public static List<String[]> getArraysWithUniqueWords(List<String[]> list) {
        return list.stream().filter(arr -> Arrays.stream(arr).allMatch(s -> Collections.frequency(Arrays.asList(arr), s) == 1)).toList();
    }

    //zwroc tablice gdzie kazdy string ma unikalne litery
    public static List<String[]> uniqueLettersInString(List<String[]> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().filter(Objects::nonNull).filter(arr -> Arrays.stream(arr).filter(Objects::nonNull).allMatch(s -> s.chars().distinct().count() == s.length())).toList();
    }

    public static List<String> onlyMenNames(List<String> list) {

        return list.stream().filter(n -> !n.endsWith("a")).toList();
    }

    public static double calcAvgOfList(List<Double> list) {
        return list.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public static List<int[]> listofArraysOddEx(List<int[]> list) {
        return list.stream().filter(arr -> arr.length % 2 == 0).filter(arr -> IntStream.range(0, arr.length - 1).allMatch(i -> arr[i] <= arr[i + 1])).toList();
    }

    public static int getMinOrMaxElement(int[] array, IntBinaryOperator ibo) {
        return Arrays.stream(array).reduce(Integer::max)
                // .reduce(ibo)
                .orElseThrow();
    }

    public static String getLongestWord(String[] array, BinaryOperator<String> sbo) {
        return Arrays.stream(array)
                //.reduce(BinaryOperator.maxBy(Comparator.comparingInt(String::length)))
                .reduce((a, b) -> a.length() > b.length() ? a : b)
                // .reduce(sbo)
                .orElseThrow();
    }

    //napisz z pomoca Stream.iterate metorde ktora zwraca liste elementow od 1 do n
    public static List<Integer> getList(int n) {
        List<Integer> list = IntStream.range(1, n).boxed().toList();
        return list;
    }

    public static List<Integer> getList2(int n) {
        return Stream.iterate(1, i -> i + 1).limit(n).toList();
    }


    public static List<Integer> generateNumberEvenOrOdd(int n, Predicate<Integer> predicate) {
        return Stream.iterate(0, i -> i + 1).filter(x -> x % 2 == 0).limit(100).toList();
    }

    public static List<Integer> generateFibo(int n) {
        return Stream.iterate(new int[]{1, 1}, tab -> new int[]{tab[1], tab[0] + tab[1]}).limit(n).map(tab -> tab[0]).toList();
    }

    public static int sumOfSequenceFibo2(int n) {
        return Stream.iterate(new int[]{1, 1}, tab -> new int[]{tab[1], tab[0] + tab[1]}).limit(n).map(tab -> tab[0]).mapToInt(Integer::intValue).sum();
    }

    public static List<Integer> getPrimeNumbers(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(Main::isPrime).toList();
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        return !IntStream.rangeClosed(2, (int) Math.sqrt(n)).anyMatch(i -> n % 1 == 0);
    }

    public static List<int[]> getListOfArraysWithOddAndSorted(List<int[]> list) {
        return list.stream().filter(arr -> arr.length % 2 == 0)
                .filter(arr -> IntStream.range(0, arr.length - 1).allMatch(i -> arr[i] <= arr[i + 1])).toList();
    }

    public static int[] uniqueEl(int[] arr) {
        return Optional.ofNullable(arr).stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();
    }

    public static List<Integer> uniqueElString(List<Integer> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).toList();
    }

    public static List<Integer> uniqureElInteger1(List<Integer> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().filter(Objects::nonNull).filter(x -> Collections.frequency(list, x) == 1).toList();
    }

    public static List<List<Integer>> uniequListElements(List<List<Integer>> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(l -> l.stream().count() == l.stream().distinct().count())
                .toList();
    }

    public static List<List<Integer>> uniqueListList2(List<List<Integer>> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList)
                .stream().map(sub -> sub.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().filter(e -> e.getValue() == 1)
                        .map(Map.Entry::getKey).toList()).toList();
    }

    public static List<List<String>> uniqueListListString(List<List<String>> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().filter(Objects::nonNull)
                .filter(sub -> sub.stream()
                        .count() == sub.stream()
                        .distinct()
                        .count()).toList();
    }

    public static List<List<String>> uniqueListListStringLetters(List<List<String>> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream()
                .filter(Objects::nonNull)
                .filter(sub -> sub.stream()
                        .filter(Objects::nonNull)
                        .allMatch(s -> s.chars()
                                .count() == s.chars().distinct().count())).toList();
    }

    public static List<List<String>> uniqueArrays(List<List<String>> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream()
                .filter(Objects::nonNull)
                .filter(sub -> Collections.frequency(list, sub) == 1)
                .toList();
    }

}
