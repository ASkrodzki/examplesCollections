package pl.tmobile.stream.examples.list;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>(List.of("AREK", "Arkadiusz", "Tomek", "Ala", "jakub", "Jan"));

        List<Integer> integerList = new LinkedList<>(Arrays.asList(5, 4, 12, 4, 5, 66, -5, -1, 2));

        List<Double> doubleList = new ArrayList<>(Arrays.asList(5.0, 2.5, -1.23, -11.4, 22.4, 25.00));

        System.out.println(removeDuplicate(stringList));
        System.out.println(sortAlph(stringList));
        System.out.println(sortByLength(stringList));
        Comparator<String> comparator1 = (a, b) -> Integer.compare(a.length(), b.length());

        System.out.println(sortWithComparator(stringList, comparator1));

        System.out.println(longestString1(stringList));
        System.out.println(longestString2(stringList));

        System.out.println(countEveryName(stringList));
        System.out.println(Main.countEveryName(stringList));

        System.out.println(groupingName(stringList));
        System.out.println(countingNameByFirstLetter(stringList));

        System.out.println(bigName(stringList));
        System.out.println(startWithBigLetter(stringList));

        System.out.println(findNameLongerThan(stringList));
        System.out.println(makeNewString(stringList));

        System.out.println(joinNames(stringList));

        System.out.println(find3BiggestNumber(integerList));
        System.out.println(find3Biggest(integerList));


    }

    //Usuń duplikaty z listy.
    public static List<String> removeDuplicate(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .distinct()
                .toList();
    }
    //Posortuj listę alfabetycznie.

    public static List<String> sortAlph(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .sorted()
                .toList();
    }
    //Posortuj listę po długości stringa.

    public static List<String> sortByLength(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .sorted(Comparator.comparing(String::length))
                .toList();
    }

    public static List<String> sortWithComparator(List<String> list, Comparator comparator) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .sorted(comparator)
                .toList();
    }


    //Znajdź najdłuższy string.

    public static String longestString1(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().orElse(null);
    }

    public static String longestString2(List<String> list) {

        String max = list.getFirst();

        for (String s : list) {
            if (s.length() > max.length()) {
                max = s;
            }
        }
        return max;
    }
    //Policz wystąpienia każdego imienia.

    public static Map<String, Long> countEveryName(List<String> list) {
        return Optional.of(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    public static Map<String, Long> countEveryName2(List<String> list) {
        Map<String, Long> resultMap = new HashMap<>();

        for (String s : list) {
            if (s != null) {
                resultMap.put(s, resultMap.getOrDefault(s, 0L) + 1);
            }
        }
        return resultMap;
    }

    //Zgrupuj imiona według pierwszej litery.

    public static Map<Character, List<String>> groupingName(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        e -> e.charAt(0)
                        // Collectors.counting()
                ));
    }

    //zlicz imiona zaczynajace sie na dana litere

    public static Map<Character, Long> countingNameByFirstLetter(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        e -> e.charAt(0),
                        Collectors.counting()
                ));
    }


    //Zwróć listę imion zapisanych wielkimi literami.

    public static List<String> bigName(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(s -> s.equals(s.toUpperCase()))
                .toList();
    }

    public static List<String> startWithBigLetter(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .toList();
    }
    //Znajdź pierwsze imię dłuższe niż 5 znaków.

    public static String findNameLongerThan(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.length() > 5)
                .sorted(Comparator.comparingInt(String::length))
                .findFirst().orElse(null);

    }
    //Połącz wszystkie imiona w jeden string oddzielony przecinkami.

    public static String makeNewString(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
    }

    public static String joinNames(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));

            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    //Znajdź 3 największe liczby bez użycia pętli.

    public static List<Integer> find3BiggestNumber(List<Integer> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
    }

    public static List<Integer> find3Biggest(List<Integer> list) {
        Collections.sort(list, Collections.reverseOrder());
        List<Integer> resultTop = list.subList(0, Math.min(3, list.size()));

        return resultTop;
    }
}
