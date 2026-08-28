package examples.map;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 5);
        map.put("B", 12);
        map.put("C", null);
        map.put("D", 20);
        map.put("E", 8);

        List<String> stringList = new ArrayList<>(Arrays.asList("Kawa", "pies", "pies", " ", "jamnik", "Arkadiusz", "Annnna"));

        Map<String, List<Integer>> mapsOfList = new HashMap<>();
        mapsOfList.put("A", Arrays.asList(1, 3, 5, 6));
        mapsOfList.put("B", Arrays.asList(1, 2, 5, 6));
        mapsOfList.put("C", Arrays.asList(1, 3, 55, 6));

        int[] arrayInt = new int[]{5, 5, 10, 15, 20};

        List<Map<String, Integer>> maps = List.of(
                Map.of("a", 1, "b", 2),
                Map.of("b", 3, "c", 4),
                Map.of("a", 5, "c", 1)
        );

        System.out.println(Arrays.toString(getUniqueTab(arrayInt)));

        System.out.println(Arrays.toString(getStringToCharArray("Alladyn")));

        Map<String, Integer> mapForSortTest = new HashMap<>();
        mapForSortTest.put("Jan", 2);
        mapForSortTest.put("Ania", 5);
        mapForSortTest.put("Ola", 1);
        mapForSortTest.put("Kamil", 12);

        System.out.println(sortByKey(mapForSortTest));
        System.out.println(sortByKey2(mapForSortTest));
        System.out.println(sortByValue(mapForSortTest));
    }

    // SORT MAP!


    public static Map<String, List<String>> fillschooldRegister() {
        Scanner sc = new Scanner(System.in);
        Map<String, List<String>> schoolRegister = new LinkedHashMap<>();

        System.out.println("Enter class and surname");
        while (true) {
            System.out.println("input class");
            String className = sc.nextLine().trim();
            if (className.equals("0"))
                break;
            schoolRegister.putIfAbsent(className, new ArrayList<>());

            System.out.println("enter surname " + className + " put 0 to cancel");
            while (true){
                System.out.println("input surname");
                String surname = sc.nextLine().trim();
                if(surname.equals("0")) {
                    break;
                }
                schoolRegister.get(className).add(surname);
            }
        }
        return schoolRegister;
    }

    public static Map<String, Integer> sortByKey(Map<String, Integer> map) {
        return new TreeMap<>(map);
    }

    public static Map<String, Integer> sortByKey2(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }


    //Mając int[], zwróć tablicę unikalnych elementów,
    // które występują więcej niż raz w oryginale. trudne


    public static int[] getUniqueTab(int[] array) {
        return Optional.ofNullable(array)
                .stream()
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static char[] getStringToCharArray(String text) {
        return text.toCharArray();

    }

    public static String[] getStringToCharArray3(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .toArray(String[]::new);
    }

    public static String[] getStringToCharArray2(String text) {
        return Arrays.stream(text.split(""))
                .toArray(String[]::new);
    }

    //odwróc mape

    public static Map<Integer, String> reverseMap(Map<String, Integer> map) {
        return Optional.ofNullable(map)
                .orElseGet(Collections::emptyMap)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey
                ));
    }

}


