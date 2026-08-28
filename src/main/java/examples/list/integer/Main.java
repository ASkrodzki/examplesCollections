package examples.list.integer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>(List.of(1, 5, 10, -20, -1, 0, 15, 15, 10, 10, 10, 15, 10));
        //Usuń wszystkie liczby parzyste.

        System.out.println(removeOdd(integerList));

        //Posortuj listę malejąco.

        System.out.println(sortLower(integerList));

        //Znajdź medianę listy.
        System.out.println(findDominant(integerList));
        //Zwróć element występujący najczęściej.
        System.out.println(findMedian(integerList));


        //Znajdź duplikaty.

        System.out.println(findDuplicates(integerList));

        //Podziel listę na grupy po 3 elementy.

        //Usuń wszystkie liczby mniejsze od x.

        //Zamień miejscami pierwszy i ostatni element.

        //Połącz dwie listy bez duplikatów.

        //Sprawdź, czy lista zawiera ciąg kolejnych liczb.
    }

    public static List<Integer> findDuplicates(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();


    }


    public static Integer findDominant(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    public static double findMedian(List<Integer> list) {
        Collections.sort(list);
        double median;
        int length = list.size();
        if (length % 2 == 0) {
            median = (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        } else {
            median = list.get(list.size() / 2);
        }
        return median;
    }

    public static List<Integer> removeOdd(List<Integer> list) {
        List<Integer> resultList = new ArrayList<>(list);
        resultList.removeIf(x -> x % 2 == 0);
        return resultList;
    }

    public static List<Integer> sortLower(List<Integer> list) {
        List<Integer> resultList = new ArrayList<>(list);
        Collections.sort(resultList, Comparator.reverseOrder());
        return resultList;
    }
}
