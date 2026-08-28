package all.list.flatMap;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        List<List<Integer>> listOfIntegerList = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8),
                List.of(9)
        );

        List<String> listOfWords = List.of("Arek", "Zosia");

        List<String> sentences = List.of(
                "Java jest super",
                "Lubię programować",
                "Spring Boot jest popularny",
                "Stream API jest wygodne"
        );
        List<List<String>> words = Arrays.asList(
                Arrays.asList("Ala", "ma", null),
                Arrays.asList("kota", null, "i"),
                Arrays.asList("psa", "Java"),
                null,
                Arrays.asList("", "Spring")
        );
        List<int[]> listOfArray = List.of(
                new int[]{5, 10, 15},
                new int[]{5, 10, 5},
                new int[]{5, 10, 12}
        );
        //FlatMap
        //Spłaszcz List<List<Integer>> do jednej listy.
        System.out.println(flatMapListOfIntegerList(listOfIntegerList));
        //Spłaszcz List<List<String>> do jednej listy bez null.
        System.out.println(flatMapListOfStringList(words));

        //Rozbij listę słów na listę liter.
        System.out.println(flatMapToLetterList(listOfWords));

        //Rozbij listę zdań na listę słów.
        System.out.println(flatMapToWordsList(sentences));


        //Rozbij zdania na słowa, usuń duplikaty i posortuj alfabetycznie.

        System.out.println(removeDuplicatesAndASort(sentences));
        //Spłaszcz listę list i zostaw tylko liczby parzyste.
        System.out.println(flatListAndTakeEven(listOfIntegerList));
        //Spłaszcz listę list, usuń duplikaty i posortuj malejąco.
        //Rozbij stringi na znaki przez flatMapToInt(String::chars) i policz znaki.
        System.out.println(countChars(listOfWords));
        //Spłaszcz List<int[]> do jednego strumienia i policz sumę.
        System.out.println(flatMapListOfInt(listOfArray));
        //Dla List<Person> zwróć unikalną listę wszystkich skilli.
    }

    private static int flatMapListOfInt(List<int[]> listOfArray) {
        return listOfArray.stream()
                .flatMapToInt(Arrays::stream)
                .reduce(0, (a, b) -> a + b);
    }

    private static long countChars(List<String> listOfWords) {
        return listOfWords.stream()
                .flatMapToInt(c -> c.chars())
                .map(c -> Character.toLowerCase(c))
                .distinct()
                .count();

    }

    private static List<Integer> flatListAndTakeEven(List<List<Integer>> listOfIntegerList) {
        return listOfIntegerList.stream()
                .flatMap(Collection::stream)
                .distinct()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .toList();
    }

    private static List<String> removeDuplicatesAndASort(List<String> sentences) {
        return sentences.stream()
                .flatMap(w -> Arrays.stream(w.split(" ")))
                .distinct()
                .sorted((a, b) -> a.compareToIgnoreCase(b))
                .toList();
    }

    private static List<String> flatMapToWordsList(List<String> sentences) {
        return Optional.ofNullable(sentences)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .flatMap(w -> Arrays.stream(w.split(" ")))
                .toList();
    }


    private static List<String> flatMapToLetterList(List<String> words) {
        return Optional.ofNullable(words)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                //.flatMap(w -> Arrays.stream(w.split("")))
                .flatMap(w -> w.chars()
                        .mapToObj(c -> String.valueOf((char) c)))
                .toList();
    }

    private static List<String> flatMapListOfStringList(List<List<String>> words) {
        return Optional.ofNullable(words)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(s -> s != null)
                .toList();
    }

    private static List<Integer> flatMapListOfIntegerList(List<List<Integer>> listOfIntegerList) {
        return listOfIntegerList.stream()
                .flatMap(Collection::stream)
                .toList();
    }
}
